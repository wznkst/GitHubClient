package com.falry.githubclient.di

import com.falry.githubclient.domain.usecase.SearchUserUseCase
import com.falry.githubclient.domain.usecase.UserDetailFetchUseCase
import com.falry.githubclient.domain.usecase.UserRepositoryFetchUseCase
import com.falry.githubclient.domain.usecase.impl.SearchUserUseCaseImpl
import com.falry.githubclient.domain.usecase.impl.UserDetailFetchUseCaseImpl
import com.falry.githubclient.domain.usecase.impl.UserRepositoryFetchUseCaseImpl
import com.falry.githubclient.repository.GithubRepository
import com.falry.githubclient.repository.impl.GithubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserCaseModule {

  @Binds
  abstract fun bindSearchUserUseCase(
    searchUserUseCaseImpl: SearchUserUseCaseImpl
  ): SearchUserUseCase

  @Binds
  abstract fun bindUserDetailFetchUseCase(
    userDetailFetchUseCaseImpl: UserDetailFetchUseCaseImpl
  ): UserDetailFetchUseCase

  @Binds
  abstract fun bindUserRepositoryFetchUseCase(
    userRepositoryFetchUseCaseImpl: UserRepositoryFetchUseCaseImpl
  ): UserRepositoryFetchUseCase
}