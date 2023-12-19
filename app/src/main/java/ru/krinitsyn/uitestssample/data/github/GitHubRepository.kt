package ru.krinitsyn.uitestssample.data.github

import ru.krinitsyn.uitestssample.models.data.GistExt

interface GitHubRepository {

    suspend fun getGists(): List<GistExt>

}