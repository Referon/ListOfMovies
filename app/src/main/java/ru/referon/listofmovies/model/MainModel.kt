package ru.referon.listofmovies.model

data class MainModel(
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)