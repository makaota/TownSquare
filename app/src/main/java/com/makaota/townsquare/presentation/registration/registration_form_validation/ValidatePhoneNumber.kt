package com.makaota.townsquare.domain.usecases.form_validation

import javax.inject.Inject


class ValidatePhoneNumber @Inject constructor() {

    /**
     * Validates a phone number using a country code.
     *
     * @param phoneNumber The phone number to validate.
     * @param countryCode The country code for validation (e.g., "ZA" for South Africa).
     * @return ValidationResult indicating success or failure.
     */
    fun validatePhoneNumber(phoneNumber: String, countryCode: String): ValidationResult {
        return try {
            val phoneUtil = com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance()
            val numberProto = phoneUtil.parse(phoneNumber, countryCode)

            if (phoneUtil.isValidNumber(numberProto)) {
                ValidationResult(successful = true)
            } else {
                ValidationResult(
                    successful = false,
                    errorMessage = "The phone number is invalid"
                )
            }
        } catch (e: com.google.i18n.phonenumbers.NumberParseException) {
            ValidationResult(
                successful = false,
                errorMessage = "Invalid phone number format: ${e.message}"
            )
        }
    }
}