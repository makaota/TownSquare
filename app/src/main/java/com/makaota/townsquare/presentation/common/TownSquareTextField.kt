package com.makaota.townsquare.presentation.common


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makaota.townsquare.R
import com.makaota.townsquare.ui.theme.TownSquareTheme
import com.makaota.townsquare.ui.theme.sourceSans3



@Composable
fun TownSquareTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    modifier: Modifier = Modifier,
    isError: Boolean,
    leadingIcon: ImageVector,
    isPasswordField: Boolean = false,
    supportingText: @Composable() (() -> Unit)? = null, // SupportingText parameter
    keyboardOptions: KeyboardOptions,
) {

    val textColor = if (isSystemInDarkTheme()) colorResource(id = R.color.white)
    else colorResource(
        id = R.color.black_color)

    val labelColor = if (isSystemInDarkTheme())colorResource(id = R.color.light_gray)
    else Color.Gray


    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    val tintColor = MaterialTheme.colorScheme.onBackground

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier,
        isError = isError,
        supportingText = supportingText,
        visualTransformation = if (isPasswordField && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            cursorColor = colorResource(id = R.color.green),
            focusedIndicatorColor = colorResource(id = R.color.green),
            unfocusedIndicatorColor = Color.Gray,
            focusedLabelColor = colorResource(id = R.color.green),
            unfocusedLabelColor = labelColor,
        ), textStyle = TextStyle(
            fontSize = 18.sp,
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Medium
        ),
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null // Provide a meaningful description if needed
            )
        },
        trailingIcon = {
            if (isPasswordField && value.isNotEmpty()) {
                val visibilityIcon =
                    if (passwordVisible) painterResource(id = R.drawable.open_eye_icon) else painterResource(id = R.drawable.close_aye)
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Image(
                        painter = visibilityIcon,
                        contentDescription = description,
                        colorFilter = ColorFilter.tint(tintColor))
                }

            }
        },
        keyboardOptions = if (label == stringResource(R.string.enter_phone_number)) {
            KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone) // Set phone keyboard for phone number field
        } else {
            keyboardOptions // Use the passed keyboardOptions for other fields
        }
    )
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun TownSquareTextFieldPreview() {
    TownSquareTheme {
        TownSquareTextField(
            value = "",
            onValueChange = {},
            label = "Enter your name",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            isError = false,
            leadingIcon = Icons.Default.Person,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone
            ),

        )

    }
}