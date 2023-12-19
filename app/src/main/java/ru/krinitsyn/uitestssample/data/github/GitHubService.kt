package ru.krinitsyn.uitestssample.data.github

import retrofit2.http.GET
import ru.krinitsyn.uitestssample.models.data.GistExt

interface GitHubService {

    @GET("gists/public")
    suspend fun getGists(): List<GistExt>

}