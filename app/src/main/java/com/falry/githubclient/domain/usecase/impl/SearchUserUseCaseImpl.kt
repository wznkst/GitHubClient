package com.falry.githubclient.domain.usecase.impl

import com.falry.githubclient.api.Result
import com.falry.githubclient.domain.model.Users
import com.falry.githubclient.domain.usecase.SearchUserUseCase
import com.falry.githubclient.repository.GithubRepository
import javax.inject.Inject

class SearchUserUseCaseImpl @Inject constructor(
  private val githubRepository: GithubRepository
) : SearchUserUseCase {

  override suspend fun execute(userId: String, page: Int, perPage: Int): Result<Users> {
    return githubRepository.getUserSearch(userId, page, perPage)
  }
}