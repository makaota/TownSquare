package com.makaota.townsquare.presentation

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField

import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.makaota.townsquare.R
import com.makaota.townsquare.Screens
import com.makaota.townsquare.data.manager.LocalUserManagerImp
import com.makaota.townsquare.domain.usecases.app_entry.AppEntryUseCases
import com.makaota.townsquare.domain.usecases.app_entry.ReadAppEntry
import com.makaota.townsquare.domain.usecases.app_entry.SaveAppEntry
import com.makaota.townsquare.presentation.login.email.EnterEmailScreen
import com.makaota.townsquare.presentation.login.email.EnterEmailViewModel
import com.makaota.townsquare.presentation.login.loginCode.LoginCodeScreen
import com.makaota.townsquare.presentation.login.password.EnterPasswordScreen
import com.makaota.townsquare.presentation.login.password.EnterPasswordViewModel
import com.makaota.townsquare.presentation.onboarding.OnBoardingScreen
import com.makaota.townsquare.presentation.onboarding.OnBoardingViewModel
import com.makaota.townsquare.presentation.registration.RegistrationScreen
import com.makaota.townsquare.presentation.registration.RegistrationViewModel
import com.makaota.townsquare.ui.theme.TownSquareTheme
import com.makaota.townsquare.ui.theme.sourceSans3
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   enableEdgeToEdge()
        // Install splash screen before setting the content
        val splashScreen = installSplashScreen()

        // Optional: Keep splash screen visible for a little longer or until specific conditions are met
        splashScreen.setKeepOnScreenCondition {
            // Define a condition to keep the splash screen displayed (e.g., while loading data)
            false // Set to false to immediately remove the splash screen
        }

        // Set the main theme after the splash screen
        setTheme(R.style.Theme_TownSquare)


        val appEntryUseCases: AppEntryUseCases by lazy{
            // Initialize the property here
            AppEntryUseCases(
                readAppEntry = ReadAppEntry(LocalUserManagerImp(applicationContext)),
                saveAppEntry = SaveAppEntry(LocalUserManagerImp(applicationContext))
            )
        }
        lifecycleScope.launch {
            appEntryUseCases.readAppEntry().collect {
                Log.d("Test",it.toString())
            }

        }
        setContent {
            TownSquareTheme {
              //  Navigation()
//                val viewModel: OnBoardingViewModel = hiltViewModel()
//                OnBoardingScreen(event = viewModel::onEvent)

//                val viewModel: RegistrationViewModel = hiltViewModel()
//                RegistrationScreen(viewModel)

//                val viewModel: EnterEmailViewModel = hiltViewModel()
//                EnterEmailScreen(viewModel)

//                val viewModel: EnterPasswordViewModel = hiltViewModel()
//                EnterPasswordScreen(viewModel)

                LoginCodeScreen()
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash_screen", builder = {

        composable(route = "splash_screen") {
            SplashScreen(navController = navController)
        }
        composable(route = Screens.SplashScreen1.route) {
            SplashScreen1(navController = navController)
        }
        composable(route = Screens.SplashScreen2.route) {
            SplashScreen2(navController = navController)
        }
        composable(route = Screens.SplashScreen3.route) {
            SplashScreen3(navController = navController)
        }
        composable(route = Screens.MainActivity.route) {
            MainActivity(navController = navController)
        }
    })
}

@Composable
fun HideSystemBar() {

    val view = LocalView.current
    val window = (view.context as Activity).window
    val insetsController = WindowCompat.getInsetsController(window, view)
    insetsController.apply {
        hide(WindowInsetsCompat.Type.systemBars())
        systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

@Composable
fun ShowSystemBars() {

    val view = LocalView.current
    val window = (view.context as Activity).window
    val insetsController = WindowCompat.getInsetsController(window, view)
    insetsController.apply {
        show(WindowInsetsCompat.Type.systemBars())
    }

}

@Composable
fun AlertDialogCloseApp(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}

@Composable
fun SplashScreen(navController: NavController) {


    HideSystemBar()

    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.3f, animationSpec = tween(
                durationMillis = 500, easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate(Screens.SplashScreen1.route)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.black_color)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "logo",
            modifier = Modifier.scale(scale.value)
        )


    }

}

@Composable
fun SplashScreen1(navController: NavController) {

    HideSystemBar()
    val scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.black_color))
            .padding(all = 16.dp)
            .verticalScroll(scrollState),
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "1/3",
                fontSize = 16.sp,
                color = colorResource(id = R.color.white),
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Medium
            )

            Text(
                modifier = Modifier.clickable {
                    navController.navigate(Screens.MainActivity.route)
                },
                text = "skip",
                fontSize = 16.sp,
                color = colorResource(id = R.color.white),
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Medium
            )


        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
        ) {
            Text(
                text = "Discover your local community",
                fontSize = 32.sp,
                color = colorResource(id = R.color.white),
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .align(Alignment.CenterHorizontally)
                .aspectRatio(1f)

        ) {
            Image(
                painter = painterResource(id = R.drawable.discover_local),
                contentDescription = "Discover Local Image",
                contentScale = ContentScale.Crop,
            )
        }

        Spacer(modifier = Modifier.padding(bottom = 16.dp))


        Text(
            text = "Connect with local shops and services.\n" +
                    "\n" +
                    "Explore the best your neighborhood has to offer.",
            fontSize = 24.sp,
            fontFamily = sourceSans3, fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.padding(bottom = 16.dp))

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {

            Box(contentAlignment = Alignment.Center) {

            }
            Image(
                painter = painterResource(id = R.drawable.circle1),
                contentDescription = "",
                modifier = Modifier.padding(bottom = 6.dp)

            )
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(Screens.SplashScreen2.route)
                }, text = "Next",
                color = colorResource(id = R.color.orange),
                fontSize = 16.sp,
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Medium

            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun ShowSplashScreen1() {
    SplashScreen1(
        navController = rememberNavController()
    )
}

@Composable
fun SplashScreen2(navController: NavController) {

    HideSystemBar()

    val scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.black_color))
            .padding(all = 16.dp)
            .verticalScroll(scrollState),
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "2/3",
                fontSize = 16.sp,
                color = colorResource(id = R.color.white),
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Medium
            )

            Text(
                modifier = Modifier.clickable {
                    navController.navigate(Screens.MainActivity.route)
                },
                text = "skip",
                fontSize = 16.sp,
                color = colorResource(id = R.color.white),
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Medium
            )

        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
        ) {
            Text(
                text = "Tailored for You",
                fontSize = 32.sp,
                color = colorResource(id = R.color.white),
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .align(Alignment.CenterHorizontally)
                .aspectRatio(1f)

        ) {
            Image(
                painter = painterResource(id = R.drawable.toilored),
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
            )
        }

        Spacer(modifier = Modifier.padding(bottom = 16.dp))


        Text(
            text = "Get personalized recommendations.\n" +
                    "\n" +
                    "Find businesses and services that match your preferences.",
            fontSize = 24.sp,
            fontFamily = sourceSans3, fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.padding(bottom = 16.dp))

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {

            Text(
                modifier = Modifier.clickable {
                    navController.navigate(Screens.SplashScreen1.route)
                }, text = "Prev",
                color = colorResource(id = R.color.white),
                fontSize = 16.sp,
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Medium

            )
            Image(
                painter = painterResource(id = R.drawable.circle2),
                contentDescription = "",
                modifier = Modifier.padding(bottom = 6.dp)

            )
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(Screens.SplashScreen3.route)
                }, text = "Next",
                color = colorResource(id = R.color.orange),
                fontSize = 16.sp,
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Medium

            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun ShowSplashScreen2() {
    SplashScreen2(navController = rememberNavController())
}

@Composable
fun SplashScreen3(navController: NavController) {


    HideSystemBar()
    val scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.black_color))
            .padding(all = 16.dp)
            .verticalScroll(scrollState),
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "3/3",
                fontSize = 16.sp,
                color = colorResource(id = R.color.white),
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Medium
            )

        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
        ) {
            Text(
                text = "Navigate Your Neighborhood",
                fontSize = 32.sp,
                color = colorResource(id = R.color.white),
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .align(Alignment.CenterHorizontally)
                .aspectRatio(1f)

        ) {
            Image(
                painter = painterResource(id = R.drawable.navigate),
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
            )
        }

        Spacer(modifier = Modifier.padding(bottom = 16.dp))


        Text(
            text = "Easy access to detailed business information.\n" +
                    "\n" +
                    "Seamlessly find and navigate to local businesses.",
            fontSize = 24.sp,
            fontFamily = sourceSans3, fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.padding(bottom = 16.dp))

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {

            Text(
                modifier = Modifier.clickable {
                    navController.navigate(Screens.SplashScreen2.route)
                }, text = "Prev",
                color = colorResource(id = R.color.white),
                fontSize = 16.sp,
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Medium

            )
            Image(
                painter = painterResource(id = R.drawable.circle3),
                contentDescription = "",
                modifier = Modifier.padding(bottom = 6.dp)

            )
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(Screens.MainActivity.route)
                }, text = "Get Started",
                color = colorResource(id = R.color.orange),
                fontSize = 16.sp,
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Medium

            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun ShowSplashScreen3() {
    SplashScreen3(navController = rememberNavController())
}

@Composable
fun MainActivity(navController: NavController) {


    ShowSystemBars()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Main Activity",
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Text(
            modifier = Modifier.clickable {
                navController.navigate(Screens.MainActivity.route)
            }, text = "Next",
            color = Color.Red,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ShowMainActivity() {
    MainActivity(navController = rememberNavController())
}


@Composable
fun EnterEmailOrSignup() {


    val scrollState = rememberScrollState()


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.black_color))
            .padding(all = 16.dp)
            .verticalScroll(scrollState),
    ) {

        Text(
            text = "skip",
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.End)
        )

        Text(
            text = "What’s your email address?",
            fontSize = 32.sp,
            fontFamily = sourceSans3, fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            modifier = Modifier
                .padding(16.dp)
        )
        Text(
            text = "To login or register please enter your email address",
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
        )


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            var emailInput by remember { mutableStateOf("") }
            val label = "Enter your email"
            val focusRequester = remember { FocusRequester() }
            var isFocused by remember { mutableStateOf(false) }

            TextField(
                value = emailInput,
                onValueChange = { emailInput = it },
                label = {
                    Text(
                        text = label,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(AbsoluteAlignment.TopLeft), // Align label to start
                        textAlign = TextAlign.Start
                    )
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.email_icon),
                        contentDescription = "email icon"
                    )
                },

                trailingIcon = {
                    if (emailInput.isNotEmpty()) {
                        Image(
                            modifier = Modifier.clickable { emailInput = "" },
                            painter = painterResource(id = R.drawable.clear_icon),
                            contentDescription = "clear icon"
                        )
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = colorResource(id = R.color.white),
                    focusedLabelColor = colorResource(id = R.color.green),
                    unfocusedLabelColor = Color.Gray,
                    unfocusedTextColor = colorResource(id = R.color.white),
                    cursorColor = colorResource(id = R.color.green),
                    focusedContainerColor = colorResource(id = R.color.black_color),
                    unfocusedContainerColor = colorResource(id = R.color.black_color),
                    focusedIndicatorColor = colorResource(id = R.color.green),
                    unfocusedIndicatorColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(AbsoluteAlignment.TopLeft) // Align label to start
                    .padding(16.dp)
                    .padding(start = 0.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged { isFocused = it.isFocused },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = sourceSans3,
                    fontWeight = FontWeight.Medium
                ),
                supportingText = {
                    Row(verticalAlignment = Alignment.CenterVertically) {


                        if (!isFocused && emailInput.isEmpty()) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Info",
                                modifier = Modifier
                                    .size(16.dp),
                                tint = (colorResource(id = R.color.orange))
                            )
                            Spacer(modifier = Modifier.width(4.dp)) // Add spacing between icon and text

                            Text(
                                "This field is required",
                                color = colorResource(id = R.color.orange)
                            )
                        } else {
                            Text("")
                        }
                    }
                }
            )
        }

        val context = LocalContext.current
        Text(
            text = "Next",
            fontSize = 16.sp,
            color = colorResource(id = R.color.green),
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 16.dp)
                .clickable {
                    Toast
                        .makeText(context, "Next Toast", Toast.LENGTH_SHORT)
                        .show()
                }
        )

        Spacer(modifier = Modifier.padding(bottom = 80.dp))

        Text(
            text = "OR",
            fontSize = 12.sp,
            color = Color.DarkGray,
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Continue with",
            fontSize = 12.sp,
            color = Color.DarkGray,
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

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
                painter = painterResource(id = R.drawable.google_svg),
                contentDescription = "Google Icon",
                modifier = Modifier
                    .size(22.dp)
                    .offset(x = (-10).dp)

            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Google",
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = colorResource(id = R.color.black_color),
                modifier = Modifier.offset(x = (-8).dp)
            )
        }

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
                painter = painterResource(id = R.drawable.facebook_svg),
                contentDescription = "Facebook Icon",
                modifier = Modifier.size(25.dp),

                )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Facebook",
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = colorResource(id = R.color.black_color),


                )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ShowEnterEmailOrSignup() {
    EnterEmailOrSignup()
}

@Composable
fun EnterPassword() {


    val scrollState = rememberScrollState()


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.black_color))
            .padding(all = 16.dp)
            .verticalScroll(scrollState),
    ) {

        Image(
            modifier = Modifier.clickable { },
            painter = painterResource(id = R.drawable.back_vector),
            contentDescription = "Back Icon"
        )

        Text(
            text = "Welcome back",
            fontSize = 32.sp,
            fontFamily = sourceSans3, fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            modifier = Modifier
                .padding(16.dp)
        )
        Text(
            text = "Please enter your password for myEmail@gmail.com",
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
        )


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            var passwordInput by remember { mutableStateOf("") }
            val label = "Enter Phone Number"
            val focusRequester = remember { FocusRequester() }
            var isFocused by remember { mutableStateOf(false) }

            TextField(
                value = passwordInput,
                onValueChange = { passwordInput = it },
                label = {
                    Text(
                        text = label,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(AbsoluteAlignment.TopLeft), // Align label to start
                        textAlign = TextAlign.Start
                    )
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.lock_icon),
                        contentDescription = "lock icon"
                    )
                },

                trailingIcon = {
                    if (passwordInput.isNotEmpty()) {
                        Image(
                            modifier = Modifier.clickable { passwordInput = "" },
                            painter = painterResource(id = R.drawable.clear_icon),
                            contentDescription = "clear icon"
                        )
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    focusedLabelColor = colorResource(id = R.color.green),
                    unfocusedLabelColor = Color.Gray,
                    unfocusedTextColor = Color.White,
                    cursorColor = colorResource(id = R.color.green),
                    focusedContainerColor = colorResource(id = R.color.black_color),
                    unfocusedContainerColor = colorResource(id = R.color.black_color),
                    focusedIndicatorColor = colorResource(id = R.color.green),
                    unfocusedIndicatorColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(AbsoluteAlignment.TopLeft) // Align label to start
                    .padding(16.dp)
                    .padding(start = 0.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged { isFocused = it.isFocused },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = sourceSans3,
                    fontWeight = FontWeight.Medium
                ),
                supportingText = {
                    Row(verticalAlignment = Alignment.CenterVertically) {


                        if (!isFocused && passwordInput.isEmpty()) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Info",
                                modifier = Modifier
                                    .size(16.dp),
                                tint = (colorResource(id = R.color.orange))
                            )
                            Spacer(modifier = Modifier.width(4.dp)) // Add spacing between icon and text

                            Text(
                                "This field is required",
                                color = colorResource(id = R.color.orange)
                            )
                        } else {
                            Text("")
                        }
                    }
                }
            )
        }

        Text(
            text = "Forgot password?",
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
        )

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
            color = colorResource(id = R.color.white),
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
        )
    }

}


@Preview(showBackground = true)
@Composable
fun ShowEnterPassword() {
    EnterPassword()
}

@Composable
fun CreateAccount() {

    val scrollState = rememberScrollState()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.black_color))
            .padding(all = 16.dp)
            .verticalScroll(scrollState),
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier.clickable { },
                painter = painterResource(id = R.drawable.back_vector),
                contentDescription = "Back Icon"
            )

            Text(
                text = "Already Registered?",
                fontSize = 16.sp,
                color = colorResource(id = R.color.white),
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = "Sign up with myemail@gmail.com and discover local businesses with ease. " +
                    "Enjoy personalized recommendations and exclusive offers. " +
                    "Join Town Square now! \uD83D\uDE80",
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            var nameInput by remember { mutableStateOf("") }
            val label = "Enter Name"
            val focusRequester = remember { FocusRequester() }
            var isFocused by remember { mutableStateOf(false) }

            TextField(
                value = nameInput,
                onValueChange = { nameInput = it },
                label = {
                    Text(
                        text = label,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(AbsoluteAlignment.TopLeft), // Align label to start
                        textAlign = TextAlign.Start
                    )
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.user_icon),
                        contentDescription = "user icon"
                    )
                },

                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    focusedLabelColor = colorResource(id = R.color.green),
                    unfocusedLabelColor = Color.Gray,
                    unfocusedTextColor = Color.White,
                    cursorColor = colorResource(id = R.color.green),
                    focusedContainerColor = colorResource(id = R.color.black_color),
                    unfocusedContainerColor = colorResource(id = R.color.black_color),
                    focusedIndicatorColor = colorResource(id = R.color.green),
                    unfocusedIndicatorColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(AbsoluteAlignment.TopLeft) // Align label to start
                    .padding(16.dp)
                    .padding(start = 0.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged { isFocused = it.isFocused },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = sourceSans3,
                    fontWeight = FontWeight.Medium
                ),
                supportingText = {
                    Row(verticalAlignment = Alignment.CenterVertically) {


                        if (!isFocused && nameInput.isEmpty()) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Info",
                                modifier = Modifier
                                    .size(16.dp),
                                tint = (colorResource(id = R.color.orange))
                            )
                            Spacer(modifier = Modifier.width(4.dp)) // Add spacing between icon and text

                            Text(
                                "This field is required",
                                color = colorResource(id = R.color.orange)
                            )
                        } else {
                            Text("")
                        }
                    }
                }
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {

            var surnameInput by remember { mutableStateOf("") }
            val label = "Enter Surname"
            val focusRequester = remember { FocusRequester() }
            var isFocused by remember { mutableStateOf(false) }

            TextField(
                value = surnameInput,
                onValueChange = { surnameInput = it },
                label = {
                    Text(
                        text = label,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(AbsoluteAlignment.TopLeft), // Align label to start
                        textAlign = TextAlign.Start
                    )
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.user_plus_icon),
                        contentDescription = "user plus icon"
                    )
                },

//                trailingIcon = {
//                    if (surnameInput.isNotEmpty()) {
//                        Image(
//                            modifier = Modifier.clickable { surnameInput = "" },
//                            painter = painterResource(id = R.drawable.clear_icon),
//                            contentDescription = "clear icon")
//                    }
//                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    focusedLabelColor = colorResource(id = R.color.green),
                    unfocusedLabelColor = Color.Gray,
                    unfocusedTextColor = Color.White,
                    cursorColor = colorResource(id = R.color.green),
                    focusedContainerColor = colorResource(id = R.color.black_color),
                    unfocusedContainerColor = colorResource(id = R.color.black_color),
                    focusedIndicatorColor = colorResource(id = R.color.green),
                    unfocusedIndicatorColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(AbsoluteAlignment.TopLeft) // Align label to start
                    .padding(16.dp)
                    .padding(start = 0.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged { isFocused = it.isFocused },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = sourceSans3,
                    fontWeight = FontWeight.Medium
                ),
                supportingText = {
                    Row(verticalAlignment = Alignment.CenterVertically) {


                        if (!isFocused && surnameInput.isEmpty()) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Info",
                                modifier = Modifier
                                    .size(16.dp),
                                tint = (colorResource(id = R.color.orange))
                            )
                            Spacer(modifier = Modifier.width(4.dp)) // Add spacing between icon and text

                            Text(
                                "This field is required",
                                color = colorResource(id = R.color.orange)
                            )
                        } else {
                            Text("")
                        }
                    }
                }
            )

        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            var mobileNumInput by remember { mutableStateOf("") }
            val label = "Enter Mobile Number"
            val focusRequester = remember { FocusRequester() }
            var isFocused by remember { mutableStateOf(false) }


            TextField(
                value = mobileNumInput,
                onValueChange = { mobileNumInput = it },
                label = {
                    Text(
                        text = label,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(AbsoluteAlignment.TopLeft), // Align label to start
                        textAlign = TextAlign.Start
                    )
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.phone_icon),
                        contentDescription = "phone icon"
                    )
                },

//                trailingIcon = {
//                    if (mobileNumInput.isNotEmpty()) {
//                        Image(
//                            modifier = Modifier.clickable { mobileNumInput = "" },
//                            painter = painterResource(id = R.drawable.clear_icon),
//                            contentDescription = "clear icon")
//                    }
//                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    focusedLabelColor = colorResource(id = R.color.green),
                    unfocusedLabelColor = Color.Gray,
                    unfocusedTextColor = Color.White,
                    cursorColor = colorResource(id = R.color.green),
                    focusedContainerColor = colorResource(id = R.color.black_color),
                    unfocusedContainerColor = colorResource(id = R.color.black_color),
                    focusedIndicatorColor = colorResource(id = R.color.green),
                    unfocusedIndicatorColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(AbsoluteAlignment.TopLeft) // Align label to start
                    .padding(16.dp)
                    .padding(start = 0.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged { isFocused = it.isFocused },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = sourceSans3,
                    fontWeight = FontWeight.Medium
                ),
                supportingText = {
                    Row(verticalAlignment = Alignment.CenterVertically) {


                        if (!isFocused && mobileNumInput.isEmpty()) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Info",
                                modifier = Modifier
                                    .size(16.dp),
                                tint = (colorResource(id = R.color.orange))
                            )
                            Spacer(modifier = Modifier.width(4.dp)) // Add spacing between icon and text

                            Text(
                                "This field is required",
                                color = colorResource(id = R.color.orange)
                            )
                        } else {
                            Text("")
                        }
                    }
                }
            )
        }

        Text(
            text = "We will send a confirmation code to your mobile number",
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            var passwordInput by remember { mutableStateOf("") }
            val label = "Enter Password"
            val focusRequester = remember { FocusRequester() }
            var isFocused by remember { mutableStateOf(false) }

            TextField(
                value = passwordInput,
                onValueChange = { passwordInput = it },
                label = {
                    Text(
                        text = label,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(AbsoluteAlignment.TopLeft), // Align label to start
                        textAlign = TextAlign.Start
                    )
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.lock_icon),
                        contentDescription = "lock icon"
                    )
                },

//                trailingIcon = {
//                    if (passwordInput.isNotEmpty()) {
//                        Image(
//                            modifier = Modifier.clickable { passwordInput = "" },
//                            painter = painterResource(id = R.drawable.clear_icon),
//                            contentDescription = "clear icon")
//                    }
//                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    focusedLabelColor = colorResource(id = R.color.green),
                    unfocusedLabelColor = Color.Gray,
                    unfocusedTextColor = Color.White,
                    cursorColor = colorResource(id = R.color.green),
                    focusedContainerColor = colorResource(id = R.color.black_color),
                    unfocusedContainerColor = colorResource(id = R.color.black_color),
                    focusedIndicatorColor = colorResource(id = R.color.green),
                    unfocusedIndicatorColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(AbsoluteAlignment.TopLeft) // Align label to start
                    .padding(16.dp)
                    .padding(start = 0.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged { isFocused = it.isFocused },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = sourceSans3,
                    fontWeight = FontWeight.Medium
                ),
                supportingText = {
                    Row(verticalAlignment = Alignment.CenterVertically) {


                        if (!isFocused && passwordInput.isEmpty()) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Info",
                                modifier = Modifier
                                    .size(16.dp),
                                tint = (colorResource(id = R.color.orange))
                            )
                            Spacer(modifier = Modifier.width(4.dp)) // Add spacing between icon and text

                            Text(
                                "This field is required",
                                color = colorResource(id = R.color.orange)
                            )
                        } else {
                            Text("")
                        }
                    }
                }
            )
        }

        Text(
            text = "Password must be at least 8 characters long and " +
                    "must contain at least one number and one letter",
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
        )

        // Spacer(modifier = Modifier.padding(bottom = 150.dp))


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
            color = colorResource(id = R.color.white),
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
        )
    }

}


@Preview(showBackground = true)
@Composable
fun ShowCreateAccount() {
    CreateAccount()
}

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
                fontWeight = FontWeight.ExtraBold,
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
                .background(if (isFocused) Color.Cyan else Color.White) // Change colors based on focus
                .padding(16.dp)
        )
}

@Composable
fun EnterCode() {

    var codeInput1 by remember { mutableStateOf("") }
    var codeInput2 by remember { mutableStateOf("") }
    var codeInput3 by remember { mutableStateOf("") }
    var codeInput4 by remember { mutableStateOf("") }
    var codeInput5 by remember { mutableStateOf("") }
    var codeInput6 by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.black_color))
            .padding(all = 16.dp)
            .verticalScroll(scrollState),
    ) {

        Image(
            modifier = Modifier.clickable { },
            painter = painterResource(id = R.drawable.back_vector),
            contentDescription = "Back Icon"
        )

        Text(
            text = "Confirm your phone number",
            fontSize = 24.sp,
            fontFamily = sourceSans3, fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            modifier = Modifier
                .padding(16.dp)
        )


        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.black_color))
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

        Text(
            text = "Place the 6-digits code sent to you at +27 67 123 4567 ",
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
        )

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
            color = colorResource(id = R.color.white),
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
        )
    }

}


@Preview(showBackground = true)
@Composable
fun ShowEnterCode() {
    EnterCode()
}








