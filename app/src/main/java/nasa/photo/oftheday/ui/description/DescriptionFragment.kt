package nasa.photo.oftheday.ui.description

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_description.*
import nasa.photo.oftheday.NasaApplication
import nasa.photo.oftheday.R
import nasa.photo.oftheday.di.component.DaggerFragmentComponent
import nasa.photo.oftheday.di.module.FragmentModule
import nasa.photo.oftheday.ui.MainSharedViewModel
import nasa.photo.oftheday.utils.common.Status
import javax.inject.Inject


class DescriptionFragment : Fragment() {


    companion object{
        const val TAG = " Description"

        fun newInstance() : DescriptionFragment{
           val args = Bundle()
           return DescriptionFragment().apply {
               this.arguments = args
           }
        }
    }


    @Inject
    lateinit var mainSharedViewModel: MainSharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setUpObservers()
    }


    private fun injectDependencies(){
            DaggerFragmentComponent.builder()
                .applicationComponent((activity?.application as NasaApplication).applicationComponent)
                .fragmentModule(FragmentModule(this))
                .build()
                .inject(this)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_description,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpObservers(){

        mainSharedViewModel.apodExposedLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {

                }

                Status.SUCCESS -> {
                    Glide.with(this)
                        .load(it.data?.hdurl)
                        .priority(Priority.HIGH)
                        .thumbnail(0.5f)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .into(ivDescImage)
                    tvDescTitle.text = it.data?.title
                    tvDescDescription.text = it.data?.explanation
                    tvDescDate.text = it.data?.date

                }

                Status.ERROR -> {
                    tvDescTitle.text = it.msg
                }


            }

        })

    }


    private fun setUpView(){
        tvDescDescription.movementMethod = ScrollingMovementMethod()
    }

}