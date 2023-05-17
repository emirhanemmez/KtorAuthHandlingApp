package com.eemmez.ktorauthhandlingapp.local

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDiModule {
    @Provides
    @Singleton
    fun provideLocalService(@ApplicationContext context: Context): LocalService =
        LocalService(context)

    @Provides
    @Singleton
    fun provideLocalRepository(localService: LocalService): LocalRepository =
        LocalRepositoryImpl(localService)
}