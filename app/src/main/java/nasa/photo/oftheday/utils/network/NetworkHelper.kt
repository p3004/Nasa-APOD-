package nasa.photo.oftheday.utils.network

/**
 * Created by Pallab Banerjee on 7/6/2020.
 */

/**
 * Interface to create FakeNetworkHelper for testing
* */
interface NetworkHelper {

    fun checkIsNetworkConnected(): Boolean

}