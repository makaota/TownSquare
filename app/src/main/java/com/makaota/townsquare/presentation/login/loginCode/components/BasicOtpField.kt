package com.makaota.townsquare.presentation.login.loginCode.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makaota.townsquare.R
import com.makaota.townsquare.ui.theme.TownSquareTheme
import com.makaota.townsquare.ui.theme.sourceSans3

@Composable
fun BasicOtpField(
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    nextFocusRequester: FocusRequester?,
    onLastFieldEntered: (() -> Unit)? = null,
) {

    var isFocused by remember { mutableStateOf(false) }

    BasicTextField(
        value = value,
        onValueChange = { input ->
            if (input.length <= 1) { // Limit input to 1 character
                onValueChange(input)
                if (input.isNotEmpty()) {
                    // Move focus to the next field if this field is not the last
                    if (nextFocusRequester != null) {
                        nextFocusRequester.requestFocus()
                    } else {
                        onLastFieldEntered?.invoke() // Hide keyboard if on the last field
                    }
                }
            }
        },
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center // Center the text within the field
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        modifier = Modifier
            .size(55.dp)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            } // Update focus state
            .focusRequester(focusRequester)
            .border(
                2.dp,
                color = colorResource(id = R.color.dark_gray),
                shape = RoundedCornerShape(50.dp)
            )
            .clip(RoundedCornerShape(50.dp))
            .background(if (isFocused && value.isNotEmpty())  Color.Cyan else Color.White) // Change colors based on focus
            .padding(16.dp)
    )
}
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun BasicOtpFieldPreview() {
    TownSquareTheme {
        BasicOtpField(
            value = "",
            onValueChange = {},
            focusRequester = remember { FocusRequester() },
            nextFocusRequester = remember { FocusRequester() },
            )
    }
}