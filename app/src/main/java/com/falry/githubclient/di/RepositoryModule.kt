package com.falry.githubclient.di

import com.falry.githubclient.repository.GithubRepository
import com.falry.githubclient.repository.impl.GithubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

  @Binds
  abstract fun bindGithubRepository(
    githubRepositoryImpl: GithubRepositoryImpl
  ): GithubRepository
}