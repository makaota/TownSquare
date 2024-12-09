package com.makaota.townsquare.presentation.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makaota.townsquare.domain.usecases.form_validation.ValidateName
import com.makaota.townsquare.domain.usecases.form_validation.ValidatePassword
import com.makaota.townsquare.domain.usecases.form_validation.ValidatePhoneNumber
import com.makaota.townsquare.domain.usecases.form_validation.ValidateSurname
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validateName: ValidateName,
    private val validateSurname: ValidateSurname,
  //  private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validatePhoneNumber: ValidatePhoneNumber

    ): ViewModel() {

    var state by mutableStateOf(RegistrationFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()


    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.NameChanged -> {
                state = state.copy(name = event.name)

            }
            is RegistrationFormEvent.SurnameChanged -> {
                state = state.copy(surname = event.surname)
            }
//            is RegistrationFormEvent.EmailChanged -> {
//                state = state.copy(email = event.email)
//
//            }
            is RegistrationFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)

            }
            is RegistrationFormEvent.PhoneNumberChanged -> {
                state = state.copy(phoneNumber =  event.phoneNumber)

            }
            RegistrationFormEvent.Submit -> {
                validateForm()
            }

            else -> {}
        }
    }

    private fun validateForm() {
        val nameResult = validateName.validateName(state.name)
        val surnameResult = validateSurname.validateSurname(state.surname)
   //     val emailResult = validateEmail.validateEmail(state.email)
        val passwordResult = validatePassword.validatePassword(state.password)
        val phoneNumberResult = validatePhoneNumber.validatePhoneNumber(state.phoneNumber, "+27")

        val hasError = listOf(
            nameResult, surnameResult, passwordResult, phoneNumberResult
        ).any { !it.successful }

        // Update the state with errors while preserving inputs
        state = state.copy(
            nameError = nameResult.errorMessage,
            surnameError = surnameResult.errorMessage,
            passwordError = passwordResult.errorMessage,
            phoneError = phoneNumberResult.errorMessage
        )
        if (!hasError) {
            viewModelScope.launch {
                validationEventChannel.send(ValidationEvent.Success)
            }
        }

    }

    sealed class ValidationEvent{
        object Success: ValidationEvent()
    }
}