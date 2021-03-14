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

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.navigation.AppNavigation
import com.example.androiddevchallenge.ui.welcome.Welcome

class MainActivity : AppCompatActivity() {

    private var hideStatusBar = false

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI(hideStatusBar)
    }

    private fun hideSystemUI(hideStatusBar: Boolean) {
        this.hideStatusBar = hideStatusBar

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.let {
                if (hideStatusBar) {
                    it.hide(WindowInsets.Type.systemBars())
                } else {
                    it.show(WindowInsets.Type.statusBars())
                    it.hide(WindowInsets.Type.navigationBars())
                }
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            if (hideStatusBar) {
                window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                    )
            } else {
                window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation { callBack -> hideSystemUI(callBack) }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640, showSystemUi = true)
@Composable
fun LightPreview() {
    MainActivityPreview(darkTheme = false)
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640, showSystemUi = true)
@Composable
fun DarkPreview() {
    MainActivityPreview(darkTheme = true)
}

@Composable
fun MainActivityPreview(darkTheme: Boolean = false) {
    Welcome(
        navController = rememberNavController(),
        darkTheme = darkTheme
    )
}
