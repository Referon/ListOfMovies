package ru.referon.listofmovies

import ru.referon.listofmovies.model.MainModel

data class FeedModel(
    val movie: MainModel? = null,
    val error: Boolean = false,
    val loading: Boolean = false
)
