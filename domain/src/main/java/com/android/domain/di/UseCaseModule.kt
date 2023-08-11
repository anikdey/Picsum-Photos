package com.android.domain.di

import com.android.domain.usecase.GetPictureListUseCase
import com.android.domain.usecase.GetPictureListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun provideGetPictureListUseCase(getPictureListUseCase: GetPictureListUseCaseImpl): GetPictureListUseCase

}
