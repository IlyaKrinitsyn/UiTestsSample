package ru.krinitsyn.uitestssample.data.github

import ru.krinitsyn.uitestssample.data.config.GitHubUrlProvider

class GitHubUrlProviderImpl : GitHubUrlProvider {

    override fun provideUrl() = "https://api.github.com"

}