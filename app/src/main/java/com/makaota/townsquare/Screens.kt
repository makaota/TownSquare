package com.makaota.townsquare

sealed class Screens(val route: String) {

    object SplashScreen1: Screens(route = "splash_screen1")
    object SplashScreen2: Screens(route = "splash_screen2")
    object SplashScreen3: Screens(route = "splash_screen3")
    object MainActivity: Screens(route = "main_activity")

}