package com.falry.githubclient.ui.user

import com.falry.githubclient.domain.model.Repositories
import com.falry.githubclient.domain.model.Users

sealed interface UserUiState {
  data class UserData(
    val userDetail: Users.User = Users.User(),
    val repositories: Repositories = Repositories(),
  ) : UserUiState
}
