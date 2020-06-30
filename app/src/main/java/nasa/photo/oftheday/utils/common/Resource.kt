package nasa.photo.oftheday.utils.common

/**
 * Created by Pallab Banerjee on 6/30/2020.
 */

/**
 * Wrapper class for fetching data , to be used as a State machine
 * */

data class Resource<out T> private constructor(val status: Status , val data : T?,val msg : String?) {

    companion object{

        fun <T> loading( data : T?) : Resource<T> = Resource(Status.LOADING,data,null)

        fun <T> success(data : T?) : Resource<T> = Resource(Status.SUCCESS,data,null)

        fun <T> error(data : T?,msg: String?) : Resource<T> = Resource(Status.ERROR,data,msg)

    }

}