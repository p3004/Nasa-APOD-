package nasa.photo.oftheday.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Supplier
import io.reactivex.rxjava3.schedulers.TestScheduler
import nasa.photo.oftheday.data.ApodRepository
import nasa.photo.oftheday.data.model.ApodModel
import nasa.photo.oftheday.utils.common.Resource
import nasa.photo.oftheday.utils.network.NetworkHelper
import nasa.photo.oftheday.utils.rx.TestSchedulerProvider
import okio.IOException
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception
import java.util.*

/**
 * Created by Pallab Banerjee on 7/3/2020.
 */

/**
 * class to test MainSharedViewModel
 * */

@RunWith(MockitoJUnitRunner::class)
class MainSharedViewModelTest {

    @get : Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var apodRepository: ApodRepository

    @Mock
    private lateinit var apodDataObserver: Observer<Resource<ApodModel>>

    private lateinit var testScheduler: TestScheduler

    private lateinit var mainSharedViewModel: MainSharedViewModel

    private lateinit var apodModel: ApodModel


    @Before
    fun setUp() {

        apodModel = ApodModel("xyz", "abc", "zyt", "uiykyk", "asdasdad", "gdsdf", "yytr", "wewqeqw")
        val compositeDisposable = CompositeDisposable()
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        mainSharedViewModel = MainSharedViewModel(
            networkHelper,
            compositeDisposable,
            testSchedulerProvider,
            apodRepository
        )
        mainSharedViewModel.apodExposedLiveData.observeForever(apodDataObserver)

    }


    @Test
    fun givenNoInternet_whenFetchPictureOfTheDay_shouldShowNetworkError() {

        doReturn(false)
            .`when`(networkHelper)
            .checkIsNetworkConnected()
       mainSharedViewModel.fetchPictureOfTheDay()
        verify(apodDataObserver).onChanged(Resource.error("No Internet Connection!"))

    }


    @Test
    fun givenServerResponse200_whenFetchPictureOfTheDay_shouldExposeApodDataViaLiveData(){

        doReturn(true)
            .`when`(networkHelper)
            .checkIsNetworkConnected()
        doReturn(Single.just(apodModel))
            .`when`(apodRepository)
            .getPictureToday()
        mainSharedViewModel.fetchPictureOfTheDay()
        testScheduler.triggerActions()
        verify(apodDataObserver).onChanged(Resource.success(apodModel))


    }


    @Test
    fun givenServerError_whenFetchPictureOfTheDay_shouldShowError(){

        doReturn(true)
            .`when`(networkHelper)
            .checkIsNetworkConnected()

        doReturn(Single.error<Exception>(Exception("Error")))
            .`when`(apodRepository)
            .getPictureToday()


        mainSharedViewModel.fetchPictureOfTheDay()
        testScheduler.triggerActions()
        verify(apodDataObserver).onChanged(Resource.error("Error"))


    }



    @After
    fun tearDown() {
        mainSharedViewModel.apodExposedLiveData.removeObserver(apodDataObserver)
    }

}