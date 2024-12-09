package com.makaota.townsquare.domain.usecases.form_validation

import android.util.Patterns
import javax.inject.Inject

class ValidateEmail @Inject constructor() {

    fun validateEmail(email:String): ValidationResult{
        if(email.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "This field is required"
            )
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return ValidationResult(
                successful = false,
                errorMessage = "That's not a valid email"

            )
        }

        return ValidationResult(
            successful = true
        )
    }
}