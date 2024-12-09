package com.makaota.townsquare.domain.usecases.form_validation

data class ValidationResult (
    val successful: Boolean,
    val errorMessage: String? = null,

)
