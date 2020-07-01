package nasa.photo.oftheday.di.component

import dagger.Component
import nasa.photo.oftheday.NasaApplication
import nasa.photo.oftheday.data.remote.ApiService
import nasa.photo.oftheday.di.module.ApplicationModule
import nasa.photo.oftheday.utils.network.NetworkHelper
import nasa.photo.oftheday.utils.rx.SchedulerProvider
import javax.inject.Singleton

/**
 * Created by Pallab Banerjee on 7/1/2020.
 */

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

            fun inject(application: NasaApplication)

            fun getApiService() : ApiService

            fun getNetworkHelper() : NetworkHelper

            fun getSchedulerProvider() : SchedulerProvider

}