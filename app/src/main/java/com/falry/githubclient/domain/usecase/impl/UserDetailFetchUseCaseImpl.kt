package com.falry.githubclient.domain.usecase.impl

import com.falry.githubclient.api.Result
import com.falry.githubclient.domain.model.Users
import com.falry.githubclient.domain.usecase.UserDetailFetchUseCase
import com.falry.githubclient.repository.GithubRepository
import javax.inject.Inject

class UserDetailFetchUseCaseImpl @Inject constructor(
  private val githubRepository: GithubRepository
) : UserDetailFetchUseCase {
  override suspend fun execute(userId: String): Result<Users.User> {
    return githubRepository.getUserDetail(userId)
  }
}