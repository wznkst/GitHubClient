package com.falry.githubclient.api.response

import com.falry.githubclient.domain.model.Repositories
import com.google.gson.annotations.SerializedName

data class RepositoriesResponse(
  val total_count: Int?,
  val items: List<Repository>?,
  val nextPage: Int?,
) {
  data class Repository(
    val name: String?,
    val language: String?,
    val stargazers_count: Int?,
    val description: String?,
    @SerializedName("html_url")
    val repositoryUrl: String?,
  ) {
    fun toRepository(): Repositories.Repository {
      return Repositories.Repository(
        name = name ?: "",
        language = language ?: "",
        stargazersCount = stargazers_count ?: 0,
        description = description ?: "",
        repositoryUrl = repositoryUrl ?: "",
      )
    }
  }

  fun toRepositories(): Repositories {
    return Repositories(
      totalCount = total_count ?: 0,
      repositoryList = items?.map { it.toRepository() } ?: emptyList(),
      nextPage = nextPage ?: 0,
    )
  }
}