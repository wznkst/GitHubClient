package com.falry.githubclient.ui.serach

import com.falry.githubclient.domain.model.Users

data class SearchViewModelState(
  val searchUsers: Users = Users(),
  val isError: Boolean = false,
) {
  fun toUiState(): SearchUiState {
    return if (isError) return SearchUiState.SearchUserError
    else SearchUiState.SearchUserData(searchUsers)
  }
}
