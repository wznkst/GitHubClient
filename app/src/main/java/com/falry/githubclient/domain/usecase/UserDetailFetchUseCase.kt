package com.falry.githubclient.domain.usecase

import com.falry.githubclient.api.Result
import com.falry.githubclient.domain.model.Users

interface UserDetailFetchUseCase {

  suspend fun execute(userId: String): Result<Users.User>
}