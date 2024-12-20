package com.makaota.townsquare.domain.usecases.form_validation

import javax.inject.Inject

class ValidatePassword @Inject constructor() {

    fun validatePassword(password:String): ValidationResult{
        if(password.length < 8){
            return ValidationResult(
                successful = false,
                errorMessage = "The password must contain at least 8 characters"
            )
        }

        val containsLettersAndDigits = password.any{it.isDigit()} && password.any { it.isLetter() }
        if (!containsLettersAndDigits){
            return ValidationResult(
                successful = false,
                errorMessage = "The password needs to contain at least one letter and digit"

            )
        }

        return ValidationResult(
            successful = true
        )
    }
}