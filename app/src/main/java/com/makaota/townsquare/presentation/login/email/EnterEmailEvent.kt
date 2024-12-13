package com.makaota.townsquare.presentation.login.email

import com.makaota.townsquare.presentation.registration.RegistrationFormEvent

sealed class EnterEmailEvent {
      data class EmailChanged(val email: String) : EnterEmailEvent()
      object Submit : EnterEmailEvent()
}