package nasa.photo.oftheday.di.module

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable
import nasa.photo.oftheday.data.ApodRepository
import nasa.photo.oftheday.ui.MainSharedViewModel
import nasa.photo.oftheday.utils.ViewModelProviderFactory
import nasa.photo.oftheday.utils.network.NetworkHelper
import nasa.photo.oftheday.utils.rx.SchedulerProvider

/**
 * Created by Pallab Banerjee on 7/2/2020.
 */

@Module
class FragmentModule(private val fragment : Fragment) {

    @Provides
    fun provideMainSharedViewModel(
        networkHelper: NetworkHelper,
        compositeDisposable: CompositeDisposable,
        schedulerProvider: SchedulerProvider,
        apodRepository: ApodRepository
    ) : MainSharedViewModel = ViewModelProvider(fragment.activity!!,ViewModelProviderFactory(MainSharedViewModel::class){MainSharedViewModel(networkHelper,compositeDisposable,schedulerProvider,apodRepository)}).get(MainSharedViewModel::class.java)

}