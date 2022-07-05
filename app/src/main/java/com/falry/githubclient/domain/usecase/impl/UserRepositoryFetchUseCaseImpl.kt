package com.falry.githubclient.domain.usecase.impl

import com.falry.githubclient.api.Result
import com.falry.githubclient.domain.model.Repositories
import com.falry.githubclient.domain.usecase.UserRepositoryFetchUseCase
import com.falry.githubclient.repository.GithubRepository
import javax.inject.Inject

class UserRepositoryFetchUseCaseImpl @Inject constructor(
  private val githubRepository: GithubRepository
) : UserRepositoryFetchUseCase {

  override suspend fun execute(userId: String, page: Int, perPage: Int): Result<Repositories> {
    return githubRepository.getRepositories(userId, page, perPage)
  }
}