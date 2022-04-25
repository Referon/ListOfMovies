package ru.referon.listofmovies.repository

import retrofit2.Response
import ru.referon.listofmovies.model.MainModel
import ru.referon.listofmovies.service.Api

class Repository {

    suspend fun getMovies(offset: Int):Response<MainModel> = Api.retrofitService.getMovies(offset)
}