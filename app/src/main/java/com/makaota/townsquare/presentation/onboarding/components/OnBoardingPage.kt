package com.makaota.townsquare.presentation.onboarding.components

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makaota.townsquare.R
import com.makaota.townsquare.Screens
import com.makaota.townsquare.presentation.onboarding.Page
import com.makaota.townsquare.presentation.onboarding.pages
import com.makaota.townsquare.ui.theme.TownSquareTheme
import com.makaota.townsquare.ui.theme.sourceSans3

@Composable
fun OnBoardingPage(
    modifier: Modifier= Modifier,
    page: Page
){
    val textColor = if (isSystemInDarkTheme()) colorResource(id = R.color.white) else colorResource(
        id = R.color.black_color
    )

    Column(modifier = modifier) {

        Text(
            text = page.title,
            fontSize = 32.sp,
            color = textColor,
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .align(Alignment.CenterHorizontally)
                .aspectRatio(1f),
            painter = painterResource(id = page.image),
            contentDescription = "Image",
            contentScale = ContentScale.Crop,
        )

        Text(
            text = page.description,
            fontSize = 24.sp,
            fontFamily = sourceSans3, fontWeight = FontWeight.Bold,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

    }

}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OnBoardingPagePreview(){
    TownSquareTheme {
        OnBoardingPage(page = pages[0])
    }
}