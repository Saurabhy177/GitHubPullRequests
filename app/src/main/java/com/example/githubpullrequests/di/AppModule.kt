package com.example.githubpullrequests.di

import com.example.githubpullrequests.data.remote.GitHubApi
import com.example.githubpullrequests.data.repository.GitHubRepoImpl
import com.example.githubpullrequests.domain.repository.GitHubRepo
import com.example.githubpullrequests.domain.usecases.GetClosedPullRequestsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGitHubApi(): GitHubApi {
        return Retrofit.Builder()
            .baseUrl(GitHubApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGitHubRepo(api: GitHubApi): GitHubRepo {
        return GitHubRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetClosedPullRequestsUseCase(repo: GitHubRepo) =
        GetClosedPullRequestsUseCase(repo)
}