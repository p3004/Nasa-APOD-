package nasa.photo.oftheday.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable
import nasa.photo.oftheday.NasaApplication
import nasa.photo.oftheday.data.remote.ApiService
import nasa.photo.oftheday.data.remote.RetrofitNetworking
import nasa.photo.oftheday.utils.network.NetworkHelper
import nasa.photo.oftheday.utils.rx.RxSchedulerProvider
import nasa.photo.oftheday.utils.rx.SchedulerProvider
import javax.inject.Singleton

/**
 * Created by Pallab Banerjee on 7/1/2020.
 */

@Module
class ApplicationModule(private val application: NasaApplication) {

    @Singleton
    @Provides
    fun provideNetworkHelper():NetworkHelper = NetworkHelper(application)



    @Provides
    fun provideRxSchedulerProvider() : SchedulerProvider = RxSchedulerProvider()


    @Provides
    fun provideCompositeDisposable() : CompositeDisposable = CompositeDisposable()


    @Singleton
    @Provides
    fun provideApiService() : ApiService = RetrofitNetworking.create()


}