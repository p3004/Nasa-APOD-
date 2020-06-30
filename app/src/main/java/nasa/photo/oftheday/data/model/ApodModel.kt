package nasa.photo.oftheday.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApodModel(

    @SerializedName("copyright")
    @Expose
    val copyright: String,

    @SerializedName("date")
    @Expose
    val date: String,

    @SerializedName("explanation")
    @Expose
    val explanation: String,

    @SerializedName("hdurl")
    @Expose
    val hdurl: String,

    @SerializedName("media_type")
    @Expose
    val media_type: String,

    @SerializedName("service_version")
    @Expose
    val service_version: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("url")
    @Expose
    val url: String
)