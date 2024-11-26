package com.makaota.townsquare.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makaota.townsquare.R
import com.makaota.townsquare.presentation.onboarding.components.OnBoardingPage
import com.makaota.townsquare.presentation.onboarding.pages
import com.makaota.townsquare.ui.theme.TownSquareTheme
import com.makaota.townsquare.ui.theme.sourceSans3

@Composable
fun OnBoardingButton(
    text: String,
    onClick: () -> Unit,
) {

    Button(
        onClick = onClick, colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.green),
            contentColor = colorResource(id = R.color.black_color)
        ), shape = RoundedCornerShape(size = 6.dp)
    ) {
        Text(
            color = colorResource(id = R.color.black_color),
            fontSize = 16.sp,
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            text = text,
        )
    }

}

@Composable
fun OnBoardingTextButton(
    text: String,
    onClick: () -> Unit,
) {

    val textColor = if (isSystemInDarkTheme()) colorResource(id = R.color.white) else colorResource(
        id = R.color.black_color
    )

    TextButton(onClick = onClick) {
        Text(
            color = textColor,
            fontSize = 16.sp,
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            text = text,
        )
    }

}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OnBoardingButtonPreview() {
    TownSquareTheme {
        OnBoardingButton(text = "Next", onClick = {})
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OnBoardingTextButtonPreview() {
    TownSquareTheme {
        OnBoardingTextButton(text = "Next", onClick = {})
    }
}