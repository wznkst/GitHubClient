package com.falry.githubclient.domain.usecase

import com.falry.githubclient.api.Result
import com.falry.githubclient.domain.model.Repositories

interface UserRepositoryFetchUseCase {

  suspend fun execute(
    userId: String,
    page: Int,
    perPage: Int,
  ): Result<Repositories>
}