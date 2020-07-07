package nasa.photo.oftheday.ui.main

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import nasa.photo.oftheday.NasaApplication
import nasa.photo.oftheday.R
import nasa.photo.oftheday.di.component.DaggerActivityComponent
import nasa.photo.oftheday.di.module.ActivityModule
import nasa.photo.oftheday.ui.MainSharedViewModel
import nasa.photo.oftheday.ui.date.DatePickerFragment
import nasa.photo.oftheday.ui.description.DescriptionFragment
import nasa.photo.oftheday.utils.common.Status
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainSharedViewModel: MainSharedViewModel

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injectBuilder()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainSharedViewModel.fetchPictureOfTheDay()
        setObservers()
    }

    override fun onResume() {
        super.onResume()
        tvMainDescription.setOnClickListener {
            mainViewModel.descriptionNavigation()
        }
        ivMainDatePicker.setOnClickListener {
            val newFragment = DatePickerFragment()
            newFragment.show(supportFragmentManager, DatePickerFragment.TAG)
        }

    }


    private fun injectBuilder() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NasaApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }


    private fun setObservers() {

        mainSharedViewModel.apodExposedLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    pbMain.visibility = View.VISIBLE
                    ivMainDatePicker.visibility = View.GONE
                }

                Status.SUCCESS -> {
                    Glide.with(this)
                        .load(it.data?.url)
                        .priority(Priority.IMMEDIATE)
                        .thumbnail(0.5f)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .into(ivMainApodBackgroundImage)
                    tvMainTitleText.text = it.data?.title
                    tvMainDate.text = it.data?.date
                    tvMainDescription.text = it.data?.explanation
                    ivMainDatePicker.visibility = View.VISIBLE
                    pbMain.visibility = View.GONE
                }

                Status.ERROR -> {
                    pbMain.visibility = View.GONE
                    tvMainTitleText.text = it.msg
                }


            }

        })


        mainViewModel.showDescriptionFragmentLiveData.observe(this, Observer {
            it?.getIfHandled().run {
                showDescriptionFragment()
            }


        })


    }


    fun showDescriptionFragment() {
        flMainContainer.visibility = View.VISIBLE
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment =
            supportFragmentManager.findFragmentByTag(DescriptionFragment.TAG) as DescriptionFragment?
        if (fragment == null) {
            fragment = DescriptionFragment.newInstance()
            fragmentTransaction.add(R.id.flMainContainer, fragment, DescriptionFragment.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }
        fragmentTransaction.commit()


    }


}