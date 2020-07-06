package nasa.photo.oftheday.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable
import nasa.photo.oftheday.NasaApplication
import nasa.photo.oftheday.data.FakeApiService
import nasa.photo.oftheday.data.remote.ApiService
import nasa.photo.oftheday.utils.network.FakeNetworkHelper
import nasa.photo.oftheday.utils.network.NetworkHelper
import nasa.photo.oftheday.utils.rx.RxSchedulerProvider
import nasa.photo.oftheday.utils.rx.SchedulerProvider
import javax.inject.Singleton

/**
 * Created by Pallab Banerjee on 7/6/2020.
 */

@Module
class ApplicationModuleForTest(private val application : NasaApplication){

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = FakeNetworkHelper()


    @Provides
    fun provideRxSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()


    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()


    @Singleton
    @Provides
    fun provideApiService(): ApiService = FakeApiService()




}