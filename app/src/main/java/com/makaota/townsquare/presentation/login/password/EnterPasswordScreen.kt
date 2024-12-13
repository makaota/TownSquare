package com.makaota.townsquare.presentation.login.password

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.makaota.townsquare.R
import com.makaota.townsquare.presentation.common.TownSquareButton
import com.makaota.townsquare.presentation.common.TownSquareTextField
import com.makaota.townsquare.presentation.login.email.EnterEmailEvent
import com.makaota.townsquare.presentation.login.email.EnterEmailViewModel
import com.makaota.townsquare.presentation.registration.RegistrationFormEvent
import com.makaota.townsquare.ui.theme.sourceSans3

@Composable
fun EnterPasswordScreen(viewModel: EnterPasswordViewModel = hiltViewModel<EnterPasswordViewModel>()) {

    val scrollState = rememberScrollState()

    val textColor = if (isSystemInDarkTheme()) colorResource(id = R.color.white)
    else colorResource(
        id = R.color.black_color
    )

    val labelColor = if (isSystemInDarkTheme()) Color.Gray
    else colorResource(id = R.color.light_gray)

    val backgroundColor = if (isSystemInDarkTheme()) colorResource(id = R.color.black_color)
    else colorResource(
        id = R.color.white
    )


    val tintColor = MaterialTheme.colorScheme.onBackground


    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {

        viewModel.validPasswordEvents.collect { event ->
            when (event) {
                is EnterPasswordViewModel.ValidPasswordEvent.Success -> {
                    Toast.makeText(
                        context, "Password Entered Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .padding(all = 16.dp)
            .verticalScroll(scrollState),
    ) {

        Image(
            modifier = Modifier.clickable { },
            painter = painterResource(id = R.drawable.back_vector),
            contentDescription = "Back Icon",
            colorFilter = ColorFilter.tint(tintColor)
        )

        Text(
            text = "Welcome back",
            fontSize = 32.sp,
            fontFamily = sourceSans3, fontWeight = FontWeight.SemiBold,
            color = textColor,
            modifier = Modifier
                .padding(16.dp)
        )
        Text(
            text = "Please enter your password for myEmail@gmail.com",
            fontSize = 16.sp,
            color = textColor,
            fontFamily = sourceSans3,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
        )


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val passwordFocusRequester = remember { FocusRequester() }
            var passwordIsFocused by remember { mutableStateOf(false) }
            TownSquareTextField(
                value = state.password,
                onValueChange =
                { viewModel.onEvent(EnterPasswordEvent.PasswordChanged(it)) },
                label = "Enter Password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .focusRequester(passwordFocusRequester)
                    .onFocusChanged { passwordIsFocused = it.isFocused },
                isError = state.passwordError != null,
                leadingIcon = Icons.Filled.Lock,
                isPasswordField = true,
                supportingText = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (state.passwordError != null) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Info",
                                modifier = Modifier
                                    .size(16.dp),
                                tint = (colorResource(id = R.color.orange))
                            )
                            Spacer(modifier = Modifier.width(4.dp)) // Add spacing between icon and text

                            Text(
                                text = state.passwordError.toString(),
                                color = colorResource(id = R.color.orange)
                            )
                        } else {
                            Text("")
                        }
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Unspecified
                ),

                )

            Text(
                text = "Forgot password?",
                fontSize = 16.sp,
                color = textColor,
                fontFamily = sourceSans3,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.padding(bottom = 30.dp))
        }

        TownSquareButton(
            onClick = {
                viewModel.onEvent(EnterPasswordEvent.Submit)
                Log.d("PasswordValidation", "State: $state")
            },
            text = "Continue",
            image = painterResource(id = R.drawable.arrow_right),
            modifier = Modifier,
            buttonColors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.green),
                contentColor = Color.White
            ),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = sourceSans3
            )
        )
    }


}