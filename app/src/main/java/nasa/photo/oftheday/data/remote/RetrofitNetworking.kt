package nasa.photo.oftheday.data.remote

import android.os.Build
import nasa.photo.oftheday.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Pallab Banerjee on 6/30/2020.
 */
object  RetrofitNetworking {

    fun create():ApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(OkHttpClient
                .Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                                     else   HttpLoggingInterceptor.Level.NONE     }).build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(ApiService::class.java)

}