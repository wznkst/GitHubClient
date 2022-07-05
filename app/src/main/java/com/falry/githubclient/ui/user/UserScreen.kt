package com.falry.githubclient.ui.user

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
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.falry.githubclient.R
import com.falry.githubclient.domain.model.Repositories
import com.falry.githubclient.domain.model.Users

@Composable
fun UserScreen(viewModel: UserViewModel, onClickItem: (String) -> Unit) {
  val uiState by viewModel.uiState.collectAsState()
  UserContent(uiState = uiState as UserUiState.UserData, onClickItem)
}

@Composable
fun UserContent(uiState: UserUiState.UserData, onClickItem: (String) -> Unit) {
  Column(modifier = Modifier.fillMaxSize()) {
    UserDetailContent(uiState.userDetail)
    RepositoryList(uiState.repositories, onClickItem)
  }
}

@Composable
fun UserDetailContent(user: Users.User) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(24.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Image(
      painter = rememberAsyncImagePainter(model = user.avatarUrl),
      contentDescription = null,
      modifier = Modifier
        .size(48.dp)
        .clip(RoundedCornerShape(24.dp))
    )
    Spacer(modifier = Modifier.width(16.dp))
    Column(modifier = Modifier.weight(1f)) {
      Row {
        Text(text = stringResource(id = R.string.label_user_name))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = user.userId)
      }

      Row {
        Text(text = stringResource(id = R.string.label_full_name))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = user.name)
      }

      Row {
        Text(text = stringResource(id = R.string.label_follower))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = user.followers.toString())
      }

      Row {
        Text(text = stringResource(id = R.string.label_following))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = user.following.toString())
      }
    }
  }
}

@Composable
fun RepositoryList(repositories: Repositories, onClickItem: (String) -> Unit) {
  LazyColumn {
    items(repositories.repositoryList) {
      RepositoryItem(repository = it, onClickItem)
    }
  }
}

@Composable
fun RepositoryItem(repository: Repositories.Repository, onClickItem: (String) -> Unit) {
  Column(modifier = Modifier
    .fillMaxWidth()
    .clickable(
      interactionSource = remember { MutableInteractionSource() },
      indication = rememberRipple()
    ) { onClickItem.invoke(repository.repositoryUrl) }
    .padding(horizontal = 24.dp, vertical = 8.dp)) {
    Text(text = repository.name)
    Spacer(modifier = Modifier.height(4.dp))
    Row(modifier = Modifier.fillMaxWidth()) {
      Row(modifier = Modifier.weight(1f)) {
        Text(text = stringResource(id = R.string.label_language))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = repository.language)
      }
      Row(modifier = Modifier.weight(1f)) {
        Text(text = stringResource(id = R.string.label_star))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = repository.stargazersCount.toString())
      }
    }
    Spacer(modifier = Modifier.height(4.dp))
    Text(text = stringResource(id = R.string.label_description))
    Text(text = repository.description)
  }
}