package com.makaota.townsquare.presentation.login.loginCode

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makaota.townsquare.R
import com.makaota.townsquare.presentation.BasicOtpField
import com.makaota.townsquare.ui.theme.TownSquareTheme
import com.makaota.townsquare.ui.theme.sourceSans3

@Composable
fun LoginCodeScreen(){
    var codeInput1 by remember { mutableStateOf("") }
    var codeInput2 by remember { mutableStateOf("") }
    var codeInput3 by remember { mutableStateOf("") }
    var codeInput4 by remember { mutableStateOf("") }
    var codeInput5 by remember { mutableStateOf("") }
    var codeInput6 by remember { mutableStateOf("") }
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
            text = "Confirm your phone number",
            fontSize = 24.sp,
            fontFamily = sourceSans3, fontWeight = FontWeight.SemiBold,
            color = textColor,
            modifier = Modifier
                .padding(16.dp)
        )

        Text(
            text = "Place the 6-digits code sent to you at 067 123 4567 ",
            fontSize = 16.sp,
            color = textColor,
            fontFamily = sourceSans3,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(16.dp)
        )

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor)
                .padding(all = 16.dp),
        ) {

            val focusRequesters = List(6) { FocusRequester() }
            val keyboardController = LocalSoftwareKeyboardController.current

            Row ( modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically){
                BasicOtpField(
                    value = codeInput1,
                    onValueChange = { codeInput1 = it },
                    focusRequester = focusRequesters[0],
                    nextFocusRequester = focusRequesters[1]
                )

                Spacer(modifier = Modifier.padding(end = 7.dp))

                BasicOtpField(
                    value = codeInput2,
                    onValueChange = { codeInput2 = it },
                    focusRequester = focusRequesters[1],
                    nextFocusRequester = focusRequesters[2]
                )

                Spacer(modifier = Modifier.padding(end = 7.dp))

                BasicOtpField(
                    value = codeInput3,
                    onValueChange = { codeInput3 = it },
                    focusRequester = focusRequesters[2],
                    nextFocusRequester = focusRequesters[3]
                )

                Spacer(modifier = Modifier.padding(end = 7.dp))

                BasicOtpField(
                    value = codeInput4,
                    onValueChange = { codeInput4 = it },
                    focusRequester = focusRequesters[3],
                    nextFocusRequester = focusRequesters[4]
                )
            }
            Spacer(modifier = Modifier.padding(top = 7.dp))

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                BasicOtpField(
                    value = codeInput5,
                    onValueChange = { codeInput5 = it },
                    focusRequester = focusRequesters[4],
                    nextFocusRequester = focusRequesters[5]
                )

                Spacer(modifier = Modifier.padding(start = 7.dp))

                BasicOtpField(
                    value = codeInput6,
                    onValueChange = { codeInput6 = it },
                    focusRequester = focusRequesters[5],
                    nextFocusRequester = null,
                    onLastFieldEntered = {
                        keyboardController?.hide() // Hide the keyboard after the last input
                    })
            }
        }



        Spacer(modifier = Modifier.padding(bottom = 30.dp))


        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.green),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(60.dp)
                .border(
                    3.dp,
                    color = colorResource(id = R.color.dark_gray),
                    shape = RoundedCornerShape(50.dp)
                ),
            shape = RoundedCornerShape(50.dp),


            ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "Forward Arrow Icon",
                modifier = Modifier.size(25.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Continue",
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = colorResource(id = R.color.black_color)

            )

        }
        Text(
            text = "By signing up you agree to the Town Square Terms of Service and Privacy Policy ",
            fontSize = 16.sp,
            color = textColor,
            fontFamily = sourceSans3,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
        )
    }

}
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun LoginCodeScreenPreview() {
    TownSquareTheme {
        LoginCodeScreen()
    }
}