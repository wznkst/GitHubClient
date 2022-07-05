package com.falry.githubclient.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falry.githubclient.api.Result
import com.falry.githubclient.domain.usecase.UserDetailFetchUseCase
import com.falry.githubclient.domain.usecase.UserRepositoryFetchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class UserViewModel @Inject constructor(
  private val userDetailFetchUseCase: UserDetailFetchUseCase,
  private val repositoryFetchUseCase: UserRepositoryFetchUseCase,
) : ViewModel(), CoroutineScope {
  private val viewModelJob = Job()
  override val coroutineContext: CoroutineContext
    get() = Dispatchers.IO + viewModelJob

  private val viewModelState = MutableStateFlow(UserViewModelState())

  val uiState = viewModelState.map { it.toUiState() }
    .stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value.toUiState())

  fun fetchUserDetail(userId: String) {
    launch {
      when (val result = userDetailFetchUseCase.execute(userId)) {
        is Result.Success -> {
          viewModelState.update {
            it.copy(userDetail = result.data)
          }
        }
        is Result.Error -> {}
      }
    }
  }

  fun fetchRepositories(userId: String) {
    launch {
      when (val result = repositoryFetchUseCase.execute(userId, 1, 20)) {
        is Result.Success -> {
          viewModelState.update {
            it.copy(repositories = result.data)
          }
        }
        is Result.Error -> {}
      }
    }
  }
}