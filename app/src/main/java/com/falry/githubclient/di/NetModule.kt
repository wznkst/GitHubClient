package com.falry.githubclient.di

import com.falry.githubclient.BuildConfig
import com.falry.githubclient.api.GithubApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetModule {

  private const val BASE_URL = "https://api.github.com/"

  @Qualifier
  @Retention(AnnotationRetention.BINARY)
  annotation class FileOkHttpClient

  @FileOkHttpClient
  @Singleton
  @Provides
  fun provideFileOkHttpClient() = getOkHttpClient()

  @Singleton
  @Provides
  fun createRetrofit(): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(getOkHttpClient())
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Singleton
  @Provides
  fun providesGithubApiService(retrofit: Retrofit): GithubApiService = retrofit.create(GithubApiService::class.java)

  private fun getOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
      .addInterceptor(Interceptor { chain ->
        val request: Request = chain
          .request()
          .newBuilder()
          .addHeader("User-Agent", "request")
          .addHeader("Accept", "application/vnd.github.v3+json")
          .addHeader("ContentType", "application/json")
          .addHeader("Authorization", BuildConfig.GITHUB_API_TOKEN)
          .build()
        chain.proceed(request)
      })
      .addInterceptor(interceptor)
      .build()
  }
}