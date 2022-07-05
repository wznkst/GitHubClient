package com.falry.githubclient.api.response

import com.falry.githubclient.domain.model.Users
import com.google.gson.annotations.SerializedName

data class UsersSearchResponse(
  val total_count: Int?,
  val items: List<User>?,
  val nextPage: Int?,
) {
  data class User(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("login")
    val userId: String?,
    val name: String?,
    val followers: Int?,
    val following: Int?,
  ) {
    fun toUser(): Users.User {
      return Users.User(
        avatarUrl = avatarUrl ?: "",
        userId = userId ?: "",
        name = name ?: "",
        followers = followers ?: 0,
        following = following ?: 0,
      )
    }
  }

  fun toUsers(): Users {
    return Users(
      totalCount = total_count ?: 0,
      userList = items?.map { it.toUser() } ?: emptyList(),
      nextPage = nextPage ?: 0
    )
  }
}
