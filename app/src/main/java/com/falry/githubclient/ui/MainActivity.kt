package com.falry.githubclient.ui

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.falry.githubclient.ui.serach.SearchScreen
import com.falry.githubclient.ui.serach.SearchViewModel
import com.falry.githubclient.ui.theme.GitHubClientTheme
import com.falry.githubclient.ui.user.UserScreen
import com.falry.githubclient.ui.user.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      GitHubClientTheme {
        Surface(color = MaterialTheme.colors.background) {
          val navController = rememberNavController()
          NavHost(navController = navController, startDestination = SEARCH_SCREEN) {
            composable(route = SEARCH_SCREEN) {
              val viewModel: SearchViewModel by viewModels()
              SearchScreen(viewModel,
                onClickItem = {
                  navController.navigate("$USER_SCREEN/${it.userId}")
                })
            }
            composable(
              route = "$USER_SCREEN/{$ARGUMENT_USER_ID}",
              arguments = listOf(
                navArgument(ARGUMENT_USER_ID) { type = NavType.StringType },
              )
            ) { backStackEntry ->
              val userId = backStackEntry.arguments?.getString(ARGUMENT_USER_ID) ?: ""
              val viewModel: UserViewModel by viewModels()
              viewModel.fetchUserDetail(userId)
              viewModel.fetchRepositories(userId)
              UserScreen(viewModel, onClickItem = {
                val intent = CustomTabsIntent.Builder().build()
                intent.launchUrl(this@MainActivity, Uri.parse(it))
              })
//              Screen2(onClickButton = { navController.navigateUp() })
            }
          }
        }
      }
    }
  }

  companion object {
    private const val SEARCH_SCREEN = "search_screen"
    private const val USER_SCREEN = "user_screen"
    private const val ARGUMENT_USER_ID = "userId"
  }
}
