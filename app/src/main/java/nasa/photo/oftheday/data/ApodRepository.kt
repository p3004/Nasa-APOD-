package nasa.photo.oftheday.data

import io.reactivex.rxjava3.core.Single
import nasa.photo.oftheday.data.model.ApodModel
import nasa.photo.oftheday.data.remote.ApiService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Pallab Banerjee on 7/1/2020.
 */

@Singleton
class ApodRepository @Inject constructor(
    private val apiService: ApiService
) {

      fun getPictureToday(): Single<ApodModel> = apiService.getPictureOfTheDay()

      fun getPictureByDate(date : String) : Single<ApodModel> = apiService.getPictureByDate("DEMO_KEY",date)

}