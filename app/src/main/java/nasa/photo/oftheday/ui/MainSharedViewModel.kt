package nasa.photo.oftheday.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nasa.photo.oftheday.utils.common.Resource
import io.reactivex.rxjava3.disposables.CompositeDisposable
import nasa.photo.oftheday.data.ApodRepository
import nasa.photo.oftheday.data.model.ApodModel
import nasa.photo.oftheday.utils.network.NetworkHelper
import nasa.photo.oftheday.utils.rx.SchedulerProvider

/**
 * Created by Pallab Banerjee on 7/1/2020.
 */
class MainSharedViewModel(
    private val networkHelper: NetworkHelper,
    private val compositeDisposable: CompositeDisposable,
    private val schedulerProvider: SchedulerProvider,
    private val apodRepository: ApodRepository
) : ViewModel() {

    private val _apodLiveData : MutableLiveData<Resource<ApodModel>> = MutableLiveData()
    val apodExposedLiveData : LiveData<Resource<ApodModel>>  = _apodLiveData



    private fun checkInternetConnectivity(): Boolean {
        return if (networkHelper.checkIsNetworkConnected()) {
            true
        } else {
            _apodLiveData.postValue(Resource.error( "No Internet Connection!"))
            false
        }

    }


     fun fetchPictureOfTheDay() {
        if (checkInternetConnectivity()) {

            _apodLiveData.postValue(Resource.loading(null))
            compositeDisposable.addAll(
                apodRepository.getPictureToday()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            _apodLiveData.postValue(Resource.success(it))
                        },
                        {
                            _apodLiveData.postValue(Resource.error(it.message))
                        }
                    )
            )
        }

    }





}