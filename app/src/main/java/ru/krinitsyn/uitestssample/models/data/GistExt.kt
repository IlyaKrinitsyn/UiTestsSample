package ru.krinitsyn.uitestssample.models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GistExt(
    @SerialName("url") val url: String,
    @SerialName("forks_url") val forksUrl: String,
    @SerialName("commits_url") val commitsUrl: String,
    @SerialName("id") val id: String,
//    @SerialName("files") val files: Map<String, FileExt> = emptyMap(),
    @SerialName("description") val description: String?,
    @SerialName("comments") val comments: Int,
    @SerialName("comments_url") val commentsUrl: String,
    @SerialName("owner") val owner: UserExt,
    @SerialName("truncated") val truncated: Boolean
)