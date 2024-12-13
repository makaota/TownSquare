package com.makaota.townsquare.presentation.login.password

import com.makaota.townsquare.presentation.registration.RegistrationFormEvent

sealed class EnterPasswordEvent {
    data class PasswordChanged(val password: String) : EnterPasswordEvent()
    object Submit : EnterPasswordEvent()
}