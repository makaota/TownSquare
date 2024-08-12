package com.makaota.townsquare

import android.app.Activity
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.makaota.townsquare.ui.theme.TownSquareTheme
import com.makaota.townsquare.ui.theme.sourceSans3
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TownSquareTheme {
                Navigation()
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

            Text(
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

    var textInput by remember { mutableStateOf("") }
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
            fontWeight = FontWeight.Medium,
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
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
        )


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BasicTextField(
                value = textInput,
                onValueChange = { textInput = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .border(
                        2.dp,
                        color = colorResource(id = R.color.red),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp),
                cursorBrush = SolidColor(Color.Black),
                maxLines = 1,
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = sourceSans3,
                    fontWeight = FontWeight.Medium
                ),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email Icon"
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Box(modifier = Modifier.weight(1f)) {
                            if (textInput.isEmpty()) {
                                Text(
                                    text = "Enter your email",
                                    color = Color.Black.copy(0.5f),
                                    fontFamily = sourceSans3
                                )
                            }
                            innerTextField()
                        }

                        if (textInput.isNotEmpty()) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                modifier = Modifier.clickable { textInput = "" },
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear Icon"
                            )
                        }
                    }
                }
            )
        }

        Text(
            text = "Next",
            fontSize = 16.sp,
            color = colorResource(id = R.color.orange),
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.padding(bottom = 180.dp))

        Text(
            text = "OR",
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            fontFamily = sourceSans3,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Column {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = colorResource(id = R.color.red)
            )

            Text(
                text = "Continue with",
                fontSize = 16.sp,
                color = colorResource(id = R.color.white),
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(0.dp)

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
                .height(56.dp)
                .border(
                    1.dp,
                    color = colorResource(id = R.color.highlight),
                    shape = RoundedCornerShape(10.dp)
                ),
            shape = RoundedCornerShape(10.dp),


        ) {
            Image(
                painter = painterResource(id = R.drawable.google_logo),
                contentDescription = "Google Icon",
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Google",
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
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
                .height(56.dp)
                .border(
                    1.dp,
                    color = colorResource(id = R.color.highlight),
                    shape = RoundedCornerShape(10.dp)
                ),
            shape = RoundedCornerShape(10.dp),


            ) {
            Image(
                painter = painterResource(id = R.drawable.facebook_logo),
                contentDescription = "Facebook Icon",
                modifier = Modifier.size(35.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Facebook",
                fontFamily = sourceSans3,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp

            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ShowEnterEmailOrSignup() {
    EnterEmailOrSignup()
}




