package com.makaota.townsquare.domain.usecases.form_validation

import javax.inject.Inject

class ValidateName @Inject constructor() {

    fun validateName(name:String): ValidationResult{
        if(name.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "This field is required",
            )
        }

        if(name.length < 2){
            return ValidationResult(
                successful = false,
                errorMessage = "Name must be at least 2 characters long",
            )
        }

        if(name.length > 50){
            return ValidationResult(
                successful = false,
                errorMessage = "Name cannot exceed 50 characters",
            )
        }

        if(!name.matches(Regex("^[a-zA-Z\\s'-]+$"))) {
            return ValidationResult(
                successful = false,
                errorMessage = "Name can only contain letters, spaces, hyphens, and apostrophes"
            )
        }

        if(name.trim() != name) {
            return ValidationResult(
                successful = false,
                errorMessage = "Name cannot have leading or trailing spaces"
            )
        }

        if(name.contains(Regex("\\s{2,}"))) {
            return ValidationResult(
                successful = false,
                errorMessage = "Name cannot have consecutive spaces"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}