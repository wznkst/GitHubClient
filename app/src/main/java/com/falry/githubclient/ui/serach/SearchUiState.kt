package com.falry.githubclient.ui.serach

import com.falry.githubclient.domain.model.Users

sealed interface SearchUiState {
  data class SearchUserData(
    val searchUsers: Users
  ) : SearchUiState

  object SearchUserError : SearchUiState
}