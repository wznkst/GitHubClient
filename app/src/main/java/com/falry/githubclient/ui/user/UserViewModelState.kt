package com.falry.githubclient.ui.user

import com.falry.githubclient.domain.model.Repositories
import com.falry.githubclient.domain.model.Users

data class UserViewModelState(
  val userDetail: Users.User = Users.User(),
  val repositories: Repositories = Repositories(),
) {
  fun toUiState(): UserUiState {
    return UserUiState.UserData(
      userDetail, repositories
    )
  }
}
