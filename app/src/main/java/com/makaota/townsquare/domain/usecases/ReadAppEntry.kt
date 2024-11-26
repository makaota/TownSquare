package com.makaota.townsquare.domain.usecases

import com.makaota.townsquare.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }

}