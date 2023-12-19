package ru.krinitsyn.uitestssample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import ru.krinitsyn.uitestssample.data.github.GitHubRepository
import ru.krinitsyn.uitestssample.data.github.GitHubRepositoryImpl
import ru.krinitsyn.uitestssample.data.github.GitHubService
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object GitHubModule {

    @Provides
    @Singleton
    fun provideGitHubService(
        retrofit: Retrofit
    ): GitHubService = retrofit.create()

    @Provides
    fun provideGitHubRepository(
        gitHubService: GitHubService
    ): GitHubRepository = GitHubRepositoryImpl(
        gitHubService = gitHubService
    )
}