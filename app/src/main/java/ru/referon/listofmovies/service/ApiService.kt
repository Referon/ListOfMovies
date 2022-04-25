package ru.referon.listofmovies.service

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.referon.listofmovies.BuildConfig
import ru.referon.listofmovies.model.MainModel

private val BASE_URL = "https://api.nytimes.com/svc/movies/v2/reviews/"
private val retrofit by lazy {
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
}

interface ApiService {
    @GET("all.json?api-key=${BuildConfig.API_KEY}")
    suspend fun getMovies(@Query("offset")offset: Int): Response<MainModel>

}
object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
