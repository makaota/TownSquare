package com.makaota.townsquare.presentation.login.email

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.makaota.townsquare.R
import com.makaota.townsquare.presentation.common.TownSquareButton
import com.makaota.townsquare.presentation.common.TownSquareTextField
import com.makaota.townsquare.ui.theme.TownSquareTheme
import com.makaota.townsquare.ui.theme.sourceSans3



@Composable
fun EnterEmailScreen(
    viewModel: EnterEmailViewModel = hiltViewModel<EnterEmailViewModel>(),
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

        viewModel.validEmailEvents.collect { event ->
            when (event) {
                is EnterEmailViewModel.ValidEmailEvent.Success -> {
                    Toast.makeText(
                        context, "Email Entered Successful",
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
            modifier = Modifier.clickable { navController.navigateUp()},
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


        val emailFocusRequester = remember { FocusRequester() }
        var emailIsFocused by remember { mutableStateOf(false) }
        TownSquareTextField(
            value = state.email,
            onValueChange =
            { viewModel.onEvent(EnterEmailEvent.EmailChanged(it)) },
            label = "Enter Email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .focusRequester(emailFocusRequester)
                .onFocusChanged { emailIsFocused = it.isFocused },
            isError = state.emailError != null,
            leadingIcon = Icons.Filled.Email,
            supportingText = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (state.emailError != null) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Info",
                            modifier = Modifier
                                .size(16.dp),
                            tint = (colorResource(id = R.color.orange))
                        )
                        Spacer(modifier = Modifier.width(4.dp)) // Add spacing between icon and text

                        Text(
                            text = state.emailError.toString(),
                            color = colorResource(id = R.color.orange)
                        )
                    } else {
                        Text("")
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            ),

            )

            Text(
                text = "Next",
                fontSize = 16.sp,
                color = colorResource(id = R.color.green),
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.End) // Use `CenterEnd` for horizontal alignment to the right
                    .padding(end = 16.dp) // Add padding from the end (right)
                    .clickable {
                        viewModel.onEvent(EnterEmailEvent.Submit)
                        Log.d("Valid Email", "State: $state")
                    }
            )

        Spacer(modifier = Modifier.padding(bottom = 80.dp))

        Text(
            text = "OR",
            fontSize = 12.sp,
            color = labelColor,
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Continue with",
            fontSize = 12.sp,
            color = labelColor,
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        TownSquareButton(
            onClick = {

            },
            text = "Google",
            image = painterResource(id = R.drawable.google_svg),
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
        TownSquareButton(
            onClick = {

            },
            text = "Facebook",
            image = painterResource(id = R.drawable.facebook_svg),
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
