package com.makaota.townsquare.presentation.login.password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makaota.townsquare.domain.usecases.form_validation.ValidatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterPasswordViewModel @Inject constructor(

    private val validatePassword: ValidatePassword,

    ) : ViewModel() {

    var state by mutableStateOf(EnterPasswordScreenState())

    private val validPasswordEventChannel = Channel<ValidPasswordEvent>()
    val validPasswordEvents = validPasswordEventChannel.receiveAsFlow()

    fun onEvent(event: EnterPasswordEvent) {
        when (event) {


            is EnterPasswordEvent.PasswordChanged -> {
                state = state.copy(password = event.password)

            }

            EnterPasswordEvent.Submit -> {
                validPassword()
            }

            else -> {}
        }
    }

    private fun validPassword() {
        val passwordResult = validatePassword.validatePassword(state.password)
        val hasError = !passwordResult.successful

        // Update the state with errors while preserving inputs
        state = state.copy(
            passwordError = passwordResult.errorMessage
        )
        if (!hasError) {
            viewModelScope.launch {
                validPasswordEventChannel.send(ValidPasswordEvent.Success)
            }
        }
    }

    sealed class ValidPasswordEvent {
        object Success : ValidPasswordEvent()
    }


}