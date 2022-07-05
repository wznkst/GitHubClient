package com.falry.githubclient.domain.usecase

import com.falry.githubclient.api.Result
import com.falry.githubclient.domain.model.Users

interface SearchUserUseCase {

  suspend fun execute(
    userId: String,
    page: Int,
    perPage: Int,
  ): Result<Users>
}