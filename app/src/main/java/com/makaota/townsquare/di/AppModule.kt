package com.makaota.townsquare.di

import android.app.Application
import com.makaota.townsquare.data.manager.LocalUserManagerImp
import com.makaota.townsquare.domain.manager.LocalUserManager
import com.makaota.townsquare.domain.usecases.app_entry.AppEntryUseCases
import com.makaota.townsquare.domain.usecases.app_entry.ReadAppEntry
import com.makaota.townsquare.domain.usecases.app_entry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImp(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

}