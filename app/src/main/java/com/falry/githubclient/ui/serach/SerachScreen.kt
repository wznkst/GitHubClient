package com.falry.githubclient.ui.serach

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.falry.githubclient.domain.model.Users

@Composable
fun SearchScreen(
  viewModel: SearchViewModel,
  onClickItem: (Users.User) -> Unit
) {
  val uiState by viewModel.uiState.collectAsState()
  SearchContent(uiState, onTextValueChanged = {
    viewModel.searchUser(it)
  }, onClickItem)
}

@Composable
fun SearchContent(
  uiState: SearchUiState,
  onTextValueChanged: (String) -> Unit,
  onClickItem: (Users.User) -> Unit
) {
  Column(modifier = Modifier.fillMaxSize()) {
    Spacer(modifier = Modifier.height(24.dp))
    var text by remember { mutableStateOf("") }
    TextField(
      value = text,
      onValueChange = {
        onTextValueChanged.invoke(it)
        text = it
      },
      modifier = Modifier
        .padding(horizontal = 24.dp)
        .fillMaxWidth(),
    )
    Spacer(modifier = Modifier.height(12.dp))
    when (uiState) {
      is SearchUiState.SearchUserData -> {
        SuggestList(uiState.searchUsers, onClickItem)
      }
      is SearchUiState.SearchUserError -> {}
    }
  }
}

@Composable
fun SuggestList(users: Users, onClickItem: (Users.User) -> Unit) {
  LazyColumn {
    items(users.userList) {
      UserItem(it, onClickItem)
    }
  }
}

@Composable
fun UserItem(user: Users.User, onClickItem: (Users.User) -> Unit) {
  Row(modifier = Modifier
    .fillMaxWidth()
    .clickable(
      interactionSource = remember { MutableInteractionSource() },
      indication = rememberRipple()
    ) { onClickItem(user) }
    .padding(horizontal = 24.dp, vertical = 12.dp),
    verticalAlignment = Alignment.CenterVertically) {
    Image(
      painter = rememberAsyncImagePainter(model = user.avatarUrl),
      contentDescription = null,
      modifier = Modifier
        .size(36.dp)
        .clip(RoundedCornerShape(18.dp))
    )
    Spacer(modifier = Modifier.width(12.dp))
    Text(text = user.userId, modifier = Modifier.weight(1f))
  }
}
