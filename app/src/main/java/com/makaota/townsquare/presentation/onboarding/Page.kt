package com.makaota.townsquare.presentation.onboarding

import androidx.annotation.DrawableRes
import com.makaota.townsquare.R

data class Page(
    val title:String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Discover your local community",
        description = "Connect with local shops and services.\n" +
                "\n" +
                "Explore the best your neighborhood has to offer.",
        image =  R.drawable.discover_local
    ),
    Page(
        title = "Specifically Designed Just for You",
        description = "Get personalized recommendations.\n" +
                "\n" +
                "Find businesses and services that match your preferences.",
        image =  R.drawable.toilored
    ),
    Page(
        title = "Navigate Your Neighborhood",
        description ="Easy access to detailed business information.\n" +
                "\n" +
                "Seamlessly find and navigate to local businesses.",
        image =  R.drawable.navigate
    )
)
