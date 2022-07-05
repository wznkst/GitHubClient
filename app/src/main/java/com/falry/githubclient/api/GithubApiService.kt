package com.falry.githubclient.api

import com.falry.githubclient.api.response.RepositoriesResponse
import com.falry.githubclient.api.response.UsersSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

  @GET("search/users?")
  suspend fun searchUsers(
    @Query("q") query: String,
    @Query("page") page: Int,
    @Query("per_page") perPage: Int
  ): Response<UsersSearchResponse>

  @GET("users/{user}")
  suspend fun getUserDetail(
    @Path("user") user: String
  ): Response<UsersSearchResponse.User>

  @GET("search/repositories?")
  suspend fun searchRepositories(
    @Query("q", encoded = true) query: String,
    @Query("page") page: Int,
    @Query("per_page") perPage: Int
  ): Response<RepositoriesResponse>
}