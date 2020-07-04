package nasa.photo.oftheday.ui.main

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_main.*
import nasa.photo.oftheday.NasaApplication
import nasa.photo.oftheday.R
import nasa.photo.oftheday.di.component.DaggerActivityComponent
import nasa.photo.oftheday.di.module.ActivityModule
import nasa.photo.oftheday.ui.MainSharedViewModel
import nasa.photo.oftheday.utils.common.Status
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainSharedViewModel: MainSharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injectBuilder()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainSharedViewModel.fetchPictureOfTheDay()
        setObservers()
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
                        .load(it.data?.hdurl)
                        .priority(Priority.HIGH)
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


    }


}