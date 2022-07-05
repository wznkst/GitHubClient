package com.falry.githubclient.domain.model

data class Users(
  val totalCount: Int = 0,
  val userList: List<User> = emptyList(),
  val nextPage: Int = 0,
) {
  data class User(
    val avatarUrl: String = "",
    val userId: String = "",
    val name: String = "",
    val followers: Int = 0,
    val following: Int = 0,
  )
}