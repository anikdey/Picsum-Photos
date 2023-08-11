package com.android.domain.di

import com.android.domain.repository.PicSumRepository
import com.android.domain.repository.PicSumRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class  RepositoryModule {

    @Binds
    @Singleton
    abstract fun providePicSumRepository(repository: PicSumRepositoryImpl): PicSumRepository

}