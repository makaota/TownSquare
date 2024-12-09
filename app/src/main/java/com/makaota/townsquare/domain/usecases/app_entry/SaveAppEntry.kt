package com.makaota.townsquare.domain.usecases.app_entry

import com.makaota.townsquare.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()

    }
}