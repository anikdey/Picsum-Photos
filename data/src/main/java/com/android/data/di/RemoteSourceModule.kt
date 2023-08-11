package com.android.data.di

import com.android.data.remote.PicSumRemoteSource
import com.android.data.remote.PicSumRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class  RemoteSourceModule {

    @Binds
    @Singleton
    abstract fun providePicSumRemoteSource(loginRemoteSource: PicSumRemoteSourceImpl): PicSumRemoteSource

}