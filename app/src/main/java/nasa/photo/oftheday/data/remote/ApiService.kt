package nasa.photo.oftheday.data.remote

import io.reactivex.rxjava3.core.Single
import nasa.photo.oftheday.data.model.ApodModel
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.ThreadPoolExecutor

/**
 * Created by Pallab Banerjee on 6/30/2020.
 */
interface ApiService {

    @GET(Endpoints.APOD)
    fun getPictureOfTheDay(
        @Query("api_key") apiKey : String = "DEMO_KEY"
    ): Single<ApodModel>

    @GET(Endpoints.APOD)
    fun getPictureByDate(
        @Query("api_key") apiKey : String? ,
        @Query("date") date : String?
    ):Single<ApodModel>


}