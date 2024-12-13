package com.makaota.townsquare.presentation.registration

sealed class RegistrationFormEvent {
    data class NameChanged(val name: String) : RegistrationFormEvent()
    data class SurnameChanged(val surname: String) : RegistrationFormEvent()
    data class PasswordChanged(val password: String) : RegistrationFormEvent()
    data class PhoneNumberChanged(val phoneNumber: String) : RegistrationFormEvent()
    object Submit : RegistrationFormEvent()
}