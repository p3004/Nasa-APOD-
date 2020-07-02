package nasa.photo.oftheday.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import nasa.photo.oftheday.NasaApplication
import nasa.photo.oftheday.R
import nasa.photo.oftheday.di.component.DaggerActivityComponent
import nasa.photo.oftheday.di.module.ActivityModule
import nasa.photo.oftheday.ui.MainSharedViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainSharedViewModel: MainSharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injectBuilder()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun injectBuilder(){
        DaggerActivityComponent.builder()
            .applicationComponent((application as NasaApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }




}