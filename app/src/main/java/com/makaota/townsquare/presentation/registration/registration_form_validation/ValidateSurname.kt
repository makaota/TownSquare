package com.makaota.townsquare.domain.usecases.form_validation


import javax.inject.Inject

class ValidateSurname @Inject constructor(){

    fun validateSurname(surname:String): ValidationResult{
        if(surname.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "This field is required",
            )
        }

        if(surname.length < 3){
            return ValidationResult(
                successful = false,
                errorMessage = "Surname must be at least 3 characters long",
            )
        }

        if(surname.length > 50){
            return ValidationResult(
                successful = false,
                errorMessage = "Surname cannot exceed 50 characters",
            )
        }

        if(!surname.matches(Regex("^[a-zA-Z\\s'-]+$"))) {
            return ValidationResult(
                successful = false,
                errorMessage = "Surname can only contain letters, spaces, hyphens, and apostrophes"
            )
        }

        if(surname.trim() != surname) {
            return ValidationResult(
                successful = false,
                errorMessage = "Surname cannot have leading or trailing spaces"
            )
        }

        if(surname.contains(Regex("\\s{2,}"))) {
            return ValidationResult(
                successful = false,
                errorMessage = "Surname cannot have consecutive spaces"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}