package com.makaota.townsquare.presentation.navgraph

sealed class Route(
    val route: String ) {

    object OnBoardingcreen: Route("onBoardingScreen")
    object RegistrationScreen: Route("registrationScreen")
    object EnterEmailScreen: Route("enterEmailScreen")
    object EnterPasswordScreen: Route("enterPasswordScreen")
    object LoginCodeScreen: Route("loginCodeScreen")
    object MainActivity: Route("mainActivity")
    object AppStartNavigation: Route("appStartNavigation")
    object AppNavigation: Route("appNavigation") // responsible for the rest of the screens
}