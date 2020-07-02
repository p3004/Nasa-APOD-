package nasa.photo.oftheday.data

import io.reactivex.rxjava3.core.Single
import nasa.photo.oftheday.data.model.ApodModel
import nasa.photo.oftheday.data.remote.ApiService
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Pallab Banerjee on 7/2/2020.
 */

@RunWith(MockitoJUnitRunner::class)
class ApodRepositoryTest {

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var apodRepository: ApodRepository

    private lateinit var apodModel: ApodModel

    private lateinit var date : String

    @Before
    fun setUp(){
        apodModel = ApodModel("xyz","abc","zyt","uiykyk","asdasdad","gdsdf","yytr","wewqeqw")
        apodRepository = ApodRepository(apiService)
        date = "2020-03-20"
    }

    @Test
    fun whenGetPictureTodayCalled_shouldRequestGetPictureOfTheDayFromApiService(){

        doReturn(Single.just(apodModel))
            .`when`(apiService)
            .getPictureOfTheDay()

        apodRepository.getPictureToday()

        verify(apiService).getPictureOfTheDay()
    }

    @Test
    fun whenGetPictureByDateCalled_shouldRequestGetPictureByDateFromApiService(){

        doReturn(Single.just(apodModel))
            .`when`(apiService)
            .getPictureByDate("DEMO_KEY",date)

        apodRepository.getPictureByDate(date)

        verify(apiService).getPictureByDate("DEMO_KEY",date)

    }

    @After
    fun dispose(){

    }

}