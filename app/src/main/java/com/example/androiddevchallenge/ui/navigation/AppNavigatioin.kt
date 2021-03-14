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
package com.example.androiddevchallenge.ui.navigation

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.home.Home
import com.example.androiddevchallenge.ui.login.Login
import com.example.androiddevchallenge.ui.welcome.Welcome

enum class NavigationId(val value: String) {
    WELCOME("welcome"),
    LOGIN("login"),
    HOME("home"),
    FAVORITES("favorites"),
    PROFILE("profile"),
    CART("cart"),
}

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen(NavigationId.HOME.value, R.string.home)
    object Favorites : Screen(NavigationId.FAVORITES.value, R.string.favorites)
    object Profile : Screen(NavigationId.PROFILE.value, R.string.profile)
    object Cart : Screen(NavigationId.CART.value, R.string.cart)
}

val bottomNavigationItems = listOf(
    Screen.Home,
    Screen.Favorites,
    Screen.Profile,
    Screen.Cart
)

@Composable
fun AppNavigation(
    darkTheme: Boolean = isSystemInDarkTheme(),
    hideStatusBar: (Boolean) -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationId.WELCOME.value) {
        composable(NavigationId.WELCOME.value) {
            hideStatusBar(false)
            Welcome(
                navController = navController
            )
        }
        composable(NavigationId.LOGIN.value) {
            hideStatusBar(false)
            Login(
                navController = navController
            )
        }
        composable(NavigationId.HOME.value) {
            hideStatusBar(darkTheme)
            Home(
                navController = navController
            )
        }
        composable(NavigationId.FAVORITES.value) {
            hideStatusBar(darkTheme)
            Home(
                navController = navController
            )
        }
        composable(NavigationId.PROFILE.value) {
            hideStatusBar(darkTheme)
            Home(
                navController = navController
            )
        }
        composable(NavigationId.CART.value) {
            hideStatusBar(darkTheme)
            Home(
                navController = navController
            )
        }
    }
}
