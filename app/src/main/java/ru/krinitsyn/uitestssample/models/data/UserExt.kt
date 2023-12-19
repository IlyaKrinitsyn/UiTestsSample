package ru.krinitsyn.uitestssample.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserExt(
    @SerialName("login") val login: String,
    @SerialName("id") val id: Long,
    @SerialName("avatar_url") val avatarUrl: String,
    @SerialName("url") val url: String,
    @SerialName("gists_url") val gistsUrl: String
)