package nasa.photo.oftheday.utils.common

import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Pallab Banerjee on 7/3/2020.
 */
/**
 *Used as a wrapper for data that is exposed via LiveData that represents an event
 * */
data class Event<out T>(private val content: T) {

    private var hasBeenHandled = AtomicBoolean(false)


    /**
     * Returns the content and prevents its use again
     **/
    fun getIfHandled(): T? = if (hasBeenHandled.getAndSet(true)) null else content


    /**
     *Returns the content even if its already handled
     * */
    fun peek(): T = content

}