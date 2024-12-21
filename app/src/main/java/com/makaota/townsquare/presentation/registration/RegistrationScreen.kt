package com.makaota.townsquare.presentation.registration

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.makaota.townsquare.R
import com.makaota.townsquare.presentation.common.TownSquareButton
import com.makaota.townsquare.presentation.common.TownSquareTextField
import com.makaota.townsquare.presentation.navgraph.Route
import com.makaota.townsquare.ui.theme.TownSquareTheme
import com.makaota.townsquare.ui.theme.sourceSans3

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = hiltViewModel<RegistrationViewModel>(),
    navController: NavController
) {

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

        viewModel.validationEvents.collect { event ->
            when (event) {
                is RegistrationViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context, "Registration Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Navigate to the next screen
                    navController.navigate(Route.EnterEmailScreen.route)
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier.clickable { },
                painter = painterResource(id = R.drawable.back_vector),
                contentDescription = "Back Icon",
                colorFilter = ColorFilter.tint(tintColor)
            )

            Text(
                text = "Already Registered?",
                fontSize = 16.sp,
                color = textColor,
                fontFamily = sourceSans3,
                fontWeight = FontWeight.SemiBold
            )
        }

        Text(
            text = "Sign up with myemail@gmail.com and discover local businesses with ease. " +
                    "Enjoy personalized recommendations and exclusive offers. " +
                    "Join Town Square now! \uD83D\uDE80",
            fontSize = 16.sp,
            color = textColor,
            fontFamily = sourceSans3,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(16.dp)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            val nameFocusRequester = remember { FocusRequester() }
            var nameIsFocused by remember { mutableStateOf(false) }

            TownSquareTextField(
                value = state.name,
                onValueChange =
                { viewModel.onEvent(RegistrationFormEvent.NameChanged(it)) },
                label = "Enter your name",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .focusRequester(nameFocusRequester)
                    .onFocusChanged { nameIsFocused = it.isFocused },
                isError = state.nameError != null,
                leadingIcon = Icons.Filled.Person,
                supportingText = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (state.nameError != null) {

                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Info",
                                modifier = Modifier
                                    .size(16.dp),
                                tint = (colorResource(id = R.color.orange))
                            )
                            Spacer(modifier = Modifier.width(4.dp)) // Add spacing between icon and text

                            Text(
                                text = state.nameError.toString(),
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


            val surnameFocusRequester = remember { FocusRequester() }
            var surnameIsFocused by remember { mutableStateOf(false) }
            TownSquareTextField(
                value = state.surname,
                onValueChange =
                { viewModel.onEvent(RegistrationFormEvent.SurnameChanged(it)) },
                label = "Enter Surname",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .focusRequester(surnameFocusRequester)
                    .onFocusChanged { surnameIsFocused = it.isFocused },
                isError = state.nameError != null,
                leadingIcon = Icons.Filled.AccountCircle,
                supportingText = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (state.surnameError != null) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Info",
                                modifier = Modifier
                                    .size(16.dp),
                                tint = (colorResource(id = R.color.orange))
                            )
                            Spacer(modifier = Modifier.width(4.dp)) // Add spacing between icon and text

                            Text(
                                text = state.surnameError.toString(),
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

            val phoneNumberFocusRequester = remember { FocusRequester() }
            var phoneNumberIsFocused by remember { mutableStateOf(false) }
            TownSquareTextField(
                value = state.phoneNumber,
                onValueChange =
                { viewModel.onEvent(RegistrationFormEvent.PhoneNumberChanged(it)) },
                label = stringResource(R.string.enter_phone_number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .focusRequester(phoneNumberFocusRequester)
                    .onFocusChanged { phoneNumberIsFocused = it.isFocused },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone
                ),
                isError = state.nameError != null,
                leadingIcon = Icons.Filled.Phone,
                supportingText = {
                    Row(verticalAlignment = Alignment.CenterVertically) {


                        if (state.phoneError != null) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Info",
                                modifier = Modifier
                                    .size(16.dp),
                                tint = (colorResource(id = R.color.orange))
                            )
                            Spacer(modifier = Modifier.width(4.dp)) // Add spacing between icon and text

                            Text(
                                text = state.phoneError.toString(),
                                color = colorResource(id = R.color.orange)
                            )
                        } else {
                            Text("e.g. +27731234567")
                        }
                    }
                },

            )


            val passwordFocusRequester = remember { FocusRequester() }
            var passwordIsFocused by remember { mutableStateOf(false) }
            TownSquareTextField(
                value = state.password,
                onValueChange =
                { viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it)) },
                label = "Enter Password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .focusRequester(passwordFocusRequester)
                    .onFocusChanged { passwordIsFocused = it.isFocused },
                isError = state.nameError != null,
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


        }
        TownSquareButton(
            onClick = {
                // Trigger form validation
                viewModel.onEvent(RegistrationFormEvent.Submit)
                    // Log or show an error message
                    Log.d("FormValidation", "Form is invalid: $state")

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


