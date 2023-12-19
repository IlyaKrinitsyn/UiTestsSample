package ru.krinitsyn.uitestssample.models.presentation

data class User(
    val login: String,
    val id: Long,
    val avatarUrl: String,
    val url: String,
    val gistsUrl: String,
)