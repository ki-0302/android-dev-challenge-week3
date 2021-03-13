/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.navigation.NavigationId
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.getMaterialColor

@Composable
fun Welcome(
    modifier: Modifier = Modifier,
    navController: NavController,
    darkTheme: Boolean = isSystemInDarkTheme()
) {
    MyTheme(darkTheme = darkTheme, systemUiColor = getMaterialColor(darkTheme).primary) {
        Scaffold { innerPadding ->
            modifier.padding(innerPadding)

            Surface(
                modifier = modifier
                    .fillMaxSize(),
                color = MaterialTheme.colors.primary
            ) {
                Image(
                    painter = painterResource(R.drawable.welcome_bg),
                    contentDescription = null,
                    alignment = Alignment.TopStart
                )

                WelcomeContent(modifier = modifier, navController = navController)
            }
        }
    }
}

@Composable
fun WelcomeContent(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    ConstraintLayout {
        val (image, logo, subtitle, createAccountButton, loginButton) = createRefs()

        Image(
            modifier = modifier
                .padding(top = 72.dp, start = 88.dp, bottom = 48.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            painter = painterResource(R.drawable.welcome_illos),
            contentDescription = null
        )

        Image(
            modifier = modifier
                .constrainAs(logo) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            painter = painterResource(R.drawable.logo),
            contentDescription = null
        )

        Text(
            modifier = modifier
                .constrainAs(subtitle) {
                    top.linkTo(logo.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .paddingFromBaseline(top = 32.dp, bottom = 40.dp),
            text = "Beautiful home garden solutions",
            style = MaterialTheme.typography.subtitle1,
        )

        Button(
            modifier = modifier
                .constrainAs(createAccountButton) {
                    top.linkTo(subtitle.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(48.dp)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary
            ),
            shape = MaterialTheme.shapes.medium,
            onClick = {}
        ) {
            Text(
                text = "Create account",
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.onSecondary
            )
        }

        TextButton(
            modifier = modifier
                .constrainAs(loginButton) {
                    top.linkTo(createAccountButton.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(56.dp)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp),
            onClick = {
                navController.navigate(NavigationId.LOGIN.value)
            }
        ) {
            Text(
                text = "Log in",
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.secondary
            )
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun WelcomeLightPreview() {
    WelcomePreview(darkTheme = false)
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun WelcomeDarkPreview() {
    WelcomePreview(darkTheme = true)
}

@Composable
fun WelcomePreview(darkTheme: Boolean = false) {
    Welcome(
        navController = rememberNavController(),
        darkTheme = darkTheme
    )
}
