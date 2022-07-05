package com.falry.githubclient.ui.serach

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falry.githubclient.api.Result
import com.falry.githubclient.domain.usecase.SearchUserUseCase
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
class SearchViewModel @Inject constructor(
  private val searchUserUseCase: SearchUserUseCase,
) : ViewModel(), CoroutineScope {
  private val viewModelJob = Job()
  override val coroutineContext: CoroutineContext
    get() = Dispatchers.IO + viewModelJob

  private val viewModelState = MutableStateFlow(SearchViewModelState())

  val uiState = viewModelState.map { it.toUiState() }
    .stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value.toUiState())

  fun searchUser(userId: String) {
    launch {
      when (val result = searchUserUseCase.execute(userId, 1, 20)) {
        is Result.Success -> {
          viewModelState.update {
            it.copy(searchUsers = result.data, isError = false)
          }
        }
        is Result.Error -> {
          viewModelState.update {
            it.copy(isError = true)
          }
        }
      }
    }
  }
}