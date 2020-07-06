package nasa.photo.oftheday

import android.app.Application
import nasa.photo.oftheday.di.component.ApplicationComponent
import nasa.photo.oftheday.di.component.DaggerApplicationComponent
import nasa.photo.oftheday.di.module.ApplicationModule

/**
 * Created by Pallab Banerjee on 7/1/2020.
 */
class NasaApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)

    }


    // Needed to replace the component with a test specific one
    fun setApplicationComponentForTest(applicationComponent: ApplicationComponent){
        this.applicationComponent = applicationComponent
    }

}