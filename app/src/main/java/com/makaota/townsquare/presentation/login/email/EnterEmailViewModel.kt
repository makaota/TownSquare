package com.makaota.townsquare.presentation.login.email

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makaota.townsquare.domain.usecases.form_validation.ValidateEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterEmailViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,

    ) : ViewModel() {

    var state by mutableStateOf(EnterEmailScreenState())

    private val validEmailEventChannel = Channel<ValidEmailEvent>()
    val validEmailEvents = validEmailEventChannel.receiveAsFlow()

    fun onEvent(event: EnterEmailEvent) {
        when (event) {

            is EnterEmailEvent.EmailChanged -> {
                state = state.copy(email = event.email)

            }

            EnterEmailEvent.Submit -> {
                validEmail()
            }

            else -> {}
        }
    }

    private fun validEmail() {

        val emailResult = validateEmail.validateEmail(state.email)
        val hasError = !emailResult.successful

        // Update the state with errors while preserving inputs
        state = state.copy(
            emailError = emailResult.errorMessage
        )
        if (!hasError) {
            viewModelScope.launch {
                validEmailEventChannel.send(ValidEmailEvent.Success)
            }
        }
    }

    sealed class ValidEmailEvent{
        object Success: ValidEmailEvent()
    }
}