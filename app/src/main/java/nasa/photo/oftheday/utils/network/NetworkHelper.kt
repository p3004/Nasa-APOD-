package nasa.photo.oftheday.utils.network

import android.accounts.NetworkErrorException
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Singleton

/**
 * Created by Pallab Banerjee on 7/1/2020.
 */

@Singleton
class NetworkHelper(private val context: Context) {

    fun checkIsNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetwork ?: return false
        val networkCapabilities = cm.getNetworkCapabilities(activeNetwork) ?: return false

        return when
        {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false

        }

    }

}