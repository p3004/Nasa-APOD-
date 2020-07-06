package nasa.photo.oftheday.data

import io.reactivex.rxjava3.core.Single
import nasa.photo.oftheday.data.model.ApodModel
import nasa.photo.oftheday.data.remote.ApiService

/**
 * Created by Pallab Banerjee on 7/6/2020.
 */

class FakeApiService : ApiService {

    override fun getPictureOfTheDay(apiKey: String): Single<ApodModel> {
        val fakeModel = ApodModel(
            "Bryan Goff",
            "2020-07-06",
            "Unspeakable beauty and unimaginable bedlam can be found together in the Orion Nebula Arguably the most famous of all astronomy nebulas, the Great Nebula in Orion is an immense interstellar molecular cloud only 1500 light-years away.  In the featured deep image shown in assigned colors, the part of the nebula's center known as M43 is shown as taken by the Hubble Space Telescope. The Great Nebula in Orion can be found with the unaided eye near the easily identifiable belt of three stars in the popular constellation Orion.  The entire Orion Nebula, including both M42 and M43 spans about 40 light years and is located in the same spiral arm of our Galaxy as the Sun.",
            "https://apod.nasa.gov/apod/image/2007/M43_HubbleGoff_4000.jpg",
            "image",
            "v1",
            "M43: Dust, Gas, and Stars in the Orion Nebula",
            "https://apod.nasa.gov/apod/image/2007/M43_HubbleGoff_960.jpg"
        )

        return Single.just(fakeModel)
    }

    override fun getPictureByDate(apiKey: String?, date: String?): Single<ApodModel> {
        TODO("Not yet implemented")
    }


}