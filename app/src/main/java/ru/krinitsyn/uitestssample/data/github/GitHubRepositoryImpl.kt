package ru.krinitsyn.uitestssample.data.github

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.krinitsyn.uitestssample.models.data.GistExt

class GitHubRepositoryImpl(
    private val gitHubService: GitHubService
) : GitHubRepository {

    override suspend fun getGists(): List<GistExt> =
        withContext(Dispatchers.Default) {
            gitHubService.getGists().filter { gist -> gist.description.isNullOrBlank().not() }
        }

}