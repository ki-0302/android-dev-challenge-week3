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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.navigation.AppNavigation
import com.example.androiddevchallenge.ui.overview.OverviewScreen
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.utils.LocalSysUiController
import com.example.androiddevchallenge.ui.utils.SystemUiController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = remember { SystemUiController(window) }
            CompositionLocalProvider(LocalSysUiController provides systemUiController) {
                MyTheme {
                    AppNavigation()
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640, showSystemUi = true)
@Composable
fun LightPreview() {
    MyTheme {
        OverviewScreenPreview(darkTheme = false)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640, showSystemUi = true)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        OverviewScreenPreview(darkTheme = true)
    }
}

@Composable
fun OverviewScreenPreview(darkTheme: Boolean = false) {
    OverviewScreen(
        navController = rememberNavController(),
        darkTheme = darkTheme
    )
}
