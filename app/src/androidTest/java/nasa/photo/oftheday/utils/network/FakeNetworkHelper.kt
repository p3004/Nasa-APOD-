package nasa.photo.oftheday.utils.network

import javax.inject.Singleton

/**
 * Created by Pallab Banerjee on 7/6/2020.
 */

@Singleton
class FakeNetworkHelper : NetworkHelper  {
    override fun checkIsNetworkConnected(): Boolean = true
}