package nasa.photo.oftheday.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nasa.photo.oftheday.utils.common.Event

/**
 * Created by Pallab Banerjee on 7/3/2020.
 */

/**
 * ViewModel to control the navigation of fragments attached to MainActivity
* */
class MainViewModel() : ViewModel() {

    private val _showDescriptionFragmentLiveData: MutableLiveData<Event<Boolean>> = MutableLiveData()

    val  showDescriptionFragmentLiveData : LiveData<Event<Boolean>> = _showDescriptionFragmentLiveData


    fun descriptionNavigation(){
        _showDescriptionFragmentLiveData.postValue(Event(true))
    }



}