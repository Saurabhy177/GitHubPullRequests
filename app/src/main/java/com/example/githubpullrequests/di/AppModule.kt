package com.example.githubpullrequests.di

import com.example.githubpullrequests.data.remote.GitHubApi
import com.example.githubpullrequests.data.repository.GitHubRepoImpl
import com.example.githubpullrequests.domain.repository.GitHubRepo
import com.example.githubpullrequests.domain.usecases.GetClosedPullRequestsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.math.log

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGitHubApi(): GitHubApi {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(GitHubApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
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