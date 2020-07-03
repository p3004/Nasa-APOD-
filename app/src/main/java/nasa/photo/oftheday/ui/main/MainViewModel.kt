package nasa.photo.oftheday.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nasa.photo.oftheday.utils.common.Event

/**
 * Created by Pallab Banerjee on 7/3/2020.
 */
class MainViewModel() : ViewModel() {

    private val showImageFragmentLiveData: MutableLiveData<Event<Boolean>> = MutableLiveData()
    private val showDescriptionFragmentLiveData: MutableLiveData<Event<Boolean>> = MutableLiveData()
    private val showVideoFragmentLiveData: MutableLiveData<Event<Boolean>> = MutableLiveData()






}