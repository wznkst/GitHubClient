package com.falry.githubclient.repository.impl

import com.falry.githubclient.api.GithubApiService
import com.falry.githubclient.api.Result
import com.falry.githubclient.domain.model.Repositories
import com.falry.githubclient.domain.model.Users
import com.falry.githubclient.repository.GithubRepository
import retrofit2.HttpException
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
  private val githubApiService: GithubApiService
) : GithubRepository {

  override suspend fun getUserSearch(
    userId: String,
    page: Int,
    perPage: Int,
  ): Result<Users> {
    try {
      val response = githubApiService.searchUsers(
        query = userId,
        page = page,
        perPage = perPage,
      )
      if (response.isSuccessful) {
        response.body()?.let {
          return Result.Success(it.toUsers())
        }
      }
      return Result.Error(HttpException(response))
    } catch (error: Throwable) {
      return Result.Error(error)
    }
  }

  override suspend fun getUserDetail(
    userId: String,
  ): Result<Users.User> {
    try {
      val response = githubApiService.getUserDetail(
        user = userId,
      )
      if (response.isSuccessful) {
        response.body()?.let {
          return Result.Success(it.toUser())
        }
      }
      return Result.Error(HttpException(response))
    } catch (error: Throwable) {
      return Result.Error(error)
    }
  }

  override suspend fun getRepositories(
    userId: String,
    page: Int,
    perPage: Int,
  ): Result<Repositories> {
    try {
      val response = githubApiService.searchRepositories(
        query = userId,
        page = page,
        perPage = perPage,
      )
      if (response.isSuccessful) {
        response.body()?.let {
          return Result.Success(it.toRepositories())
        }
      }
      return Result.Error(HttpException(response))
    } catch (error: Throwable) {
      return Result.Error(error)
    }
  }

  companion object {
    const val NETWORK_PAGE_SIZE = 30
  }
}