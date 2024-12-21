package com.makaota.townsquare.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.makaota.townsquare.presentation.login.email.EnterEmailScreen
import com.makaota.townsquare.presentation.login.email.EnterEmailViewModel
import com.makaota.townsquare.presentation.login.loginCode.LoginCodeScreen
import com.makaota.townsquare.presentation.login.password.EnterPasswordScreen
import com.makaota.townsquare.presentation.login.password.EnterPasswordViewModel
import com.makaota.townsquare.presentation.onboarding.OnBoardingScreen
import com.makaota.townsquare.presentation.onboarding.OnBoardingViewModel
import com.makaota.townsquare.presentation.registration.RegistrationScreen
import com.makaota.townsquare.presentation.registration.RegistrationViewModel


@Composable
fun NavGraph(
    startDestination: String,
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingcreen.route
        ) {
            composable(route = Route.OnBoardingcreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = viewModel::onEvent)
            }

        }

        // Login/Registration app navigation
        navigation(
            route = Route.AppNavigation.route,
            startDestination = Route.RegistrationScreen.route
        ) {
            composable(route = Route.RegistrationScreen.route) {
                val viewModel: RegistrationViewModel = hiltViewModel()
                RegistrationScreen(viewModel,navController)
            }
            composable(route = Route.EnterEmailScreen.route) {
                val viewModel: EnterEmailViewModel = hiltViewModel()
                EnterEmailScreen(viewModel, navController)
            }
            composable(route = Route.EnterPasswordScreen.route) {
                val viewModel: EnterPasswordViewModel = hiltViewModel()
                EnterPasswordScreen(viewModel)
            }
            composable(route = Route.LoginCodeScreen.route) {
                LoginCodeScreen()
            }
        }


    }
}