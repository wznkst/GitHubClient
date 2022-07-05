package com.falry.githubclient.repository

import com.falry.githubclient.api.Result
import com.falry.githubclient.domain.model.Repositories
import com.falry.githubclient.domain.model.Users

interface GithubRepository {
  suspend fun getUserSearch(
    userId: String,
    page: Int,
    perPage: Int,
  ): Result<Users>

  suspend fun getUserDetail(userId: String): Result<Users.User>

  suspend fun getRepositories(
    userId: String,
    page: Int,
    perPage: Int,
  ): Result<Repositories>
}