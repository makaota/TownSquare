package com.makaota.townsquare.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makaota.townsquare.R
import com.makaota.townsquare.ui.theme.TownSquareTheme
import com.makaota.townsquare.ui.theme.sourceSans3

@Composable
fun TownSquareButton(
    onClick: () -> Unit,
    text: String,
    image: Painter,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    imageModifier: Modifier = Modifier.size(24.dp),
    textStyle: TextStyle = TextStyle(color = Color.White, fontSize = 16.sp)
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(60.dp)
            .border(
                3.dp,
                color = colorResource(id = R.color.dark_gray),
                shape = RoundedCornerShape(50.dp)
            ),
        shape = RoundedCornerShape(50.dp),
        colors = buttonColors
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = image,
                contentDescription = contentDescription,
                modifier = imageModifier
            )
            Spacer(modifier = Modifier.width(8.dp)) // Spacing between image and text
            Text(
                text = text,
                style = textStyle
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun TownSquareButtonPreview() {
    TownSquareTheme {
        TownSquareButton(
            onClick = { /* Handle button click */ },
            text = "Continue",
            image = painterResource(id = R.drawable.arrow_right), // Replace with your drawable resource
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