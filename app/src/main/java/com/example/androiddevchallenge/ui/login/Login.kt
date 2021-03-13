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
package com.example.androiddevchallenge.ui.login

import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.navigation.NavigationId
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.getMaterialColor
import com.example.androiddevchallenge.ui.theme.lightGray

@Composable
fun Login(
    modifier: Modifier = Modifier,
    navController: NavController,
    darkTheme: Boolean = isSystemInDarkTheme()
) {
    MyTheme(darkTheme = darkTheme, systemUiColor = getMaterialColor(darkTheme).background) {
        Scaffold { innerPadding ->
            modifier.padding(innerPadding)
            Column {
                LoginContent(modifier = modifier, navController)
            }
        }
    }
}

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = modifier
                .paddingFromBaseline(top = 184.dp, bottom = 16.dp),
            text = "Log in with email",
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onBackground
        )

        InputText(
            modifier = modifier.padding(bottom = 8.dp),
            placeholder = "Email Address"
        )
        InputText(
            modifier = modifier,
            placeholder = "Password (8+ characters)"
        )

        Text(
            modifier = modifier
                .paddingFromBaseline(top = 24.dp, bottom = 16.dp),
            text = buildAnnotatedString {
                append("By clicking below, you agree to our ")

                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append("Terms of Use")
                }
                append(" and consent\n")
                append("to our ")
                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append("Privacy Policy")
                }
                append(".")
            },
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center
        )

        Button(
            modifier = modifier
                .height(48.dp)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary
            ),
            shape = MaterialTheme.shapes.medium,
            onClick = {
                navController.navigate(NavigationId.HOME.value)
            }
        ) {
            Text(
                text = "Log in",
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.onSecondary
            )
        }
    }
}

@Composable
fun InputText(
    modifier: Modifier = Modifier,
    placeholder: String = ""
) {
    var text by remember { mutableStateOf("") }

    Box(
        modifier = modifier.padding(start = 16.dp, end = 16.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        BasicTextField(
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(1.dp, lightGray, MaterialTheme.shapes.small),
            value = text,
            onValueChange = { text = it },
            textStyle = MaterialTheme.typography.body1,

        )

        if (text.isEmpty()) {
            Text(
                modifier = modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth(),
                text = placeholder
            )
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LoginLightPreview() {
    LoginPreview(darkTheme = false)
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun LoginDarkPreview() {
    LoginPreview(darkTheme = true)
}

@Composable
fun LoginPreview(darkTheme: Boolean = false) {
    Login(
        navController = rememberNavController(),
        darkTheme = darkTheme
    )
}
