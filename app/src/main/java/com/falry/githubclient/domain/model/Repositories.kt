package com.falry.githubclient.domain.model

data class Repositories(
  val totalCount: Int = 0,
  val repositoryList: List<Repository> = emptyList(),
  val nextPage: Int = 0,
) {
  data class Repository(
    val name: String = "",
    val language: String = "",
    val stargazersCount: Int = 0,
    val description: String = "",
    val repositoryUrl: String = "",
  )
}
