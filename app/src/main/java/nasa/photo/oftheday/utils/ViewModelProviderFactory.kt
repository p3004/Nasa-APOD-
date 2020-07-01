package nasa.photo.oftheday.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Singleton
import kotlin.reflect.KClass

/**
 * Created by Pallab Banerjee on 6/30/2020.
 */

/**
* A ViewModel provider factory class , for creating VieModel class with any number of dependencies in its constructor
* */

class ViewModelProviderFactory<T: ViewModel> (

    private val kClass: KClass<T>,
    private val creator : () -> T

) : ViewModelProvider.NewInstanceFactory() {


    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(kClass.java)) return creator as T
        throw (IllegalArgumentException("Unknown class name"))
    }

}