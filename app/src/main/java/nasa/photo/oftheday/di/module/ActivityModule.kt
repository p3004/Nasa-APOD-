package nasa.photo.oftheday.di.module

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable
import nasa.photo.oftheday.data.ApodRepository
import nasa.photo.oftheday.ui.MainSharedViewModel
import nasa.photo.oftheday.ui.main.MainViewModel
import nasa.photo.oftheday.utils.ViewModelProviderFactory
import nasa.photo.oftheday.utils.network.NetworkHelper
import nasa.photo.oftheday.utils.network.NetworkHelperImpl
import nasa.photo.oftheday.utils.rx.SchedulerProvider

/**
 * Created by Pallab Banerjee on 7/1/2020.
 **/


@Module
class ActivityModule(private val activity: AppCompatActivity) {

      @Provides
      fun provideMainSharedVieModel(
          networkHelperImpl: NetworkHelper,
          compositeDisposable: CompositeDisposable,
          schedulerProvider: SchedulerProvider,
          apodRepository: ApodRepository
      ) : MainSharedViewModel = ViewModelProvider(activity,ViewModelProviderFactory(MainSharedViewModel::class){
          MainSharedViewModel(networkHelperImpl,compositeDisposable,schedulerProvider,apodRepository)
      }).get(MainSharedViewModel::class.java)


    @Provides
    fun provideMainViewModel() : MainViewModel = ViewModelProvider(activity).get(MainViewModel::class.java)


}