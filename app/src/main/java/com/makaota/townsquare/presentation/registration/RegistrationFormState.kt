package com.makaota.townsquare.presentation.registration



data class RegistrationFormState(
    val name: String = "",
    val surname: String = "",
    val password: String = "",
    val phoneNumber: String = "",
    val nameError: String? = null,
    val surnameError: String? = null,
    val passwordError: String? = null,
    val phoneError: String? = null,

)