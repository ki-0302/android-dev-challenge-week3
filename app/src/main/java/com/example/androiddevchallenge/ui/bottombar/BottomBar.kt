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
package com.example.androiddevchallenge.ui.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.navigation.Screen
import com.example.androiddevchallenge.ui.navigation.bottomNavigationItems
import com.example.androiddevchallenge.ui.theme.AppIcons
import com.example.androiddevchallenge.ui.theme.Elevation
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.bottomAppBarUnSelected
import com.example.androiddevchallenge.ui.theme.getShadowColor
import com.example.androiddevchallenge.ui.utils.PREVIEW_BOTTOM_NAVIGATION_HEIGHT
import com.example.androiddevchallenge.ui.utils.PREVIEW_DARK_THEME
import com.example.androiddevchallenge.ui.utils.PREVIEW_LIGHT_THEME
import com.example.androiddevchallenge.ui.utils.PREVIEW_WIDTH

@Composable
fun BottomAppBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    darkTheme: Boolean
) {
    Column {
        Spacer(
            modifier = modifier
                .fillMaxWidth()
                .height(Elevation.bottomNavigation)
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, getShadowColor(darkTheme)),
                        0f,
                        50f,
                    )
                )
        )
        Column(
            modifier = modifier
                .height(56.dp)
                .background(MaterialTheme.colors.primary),
            verticalArrangement = Arrangement.Center
        ) {
            BottomNavigation(
                modifier = modifier,
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                elevation = 0.dp
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                bottomNavigationItems.forEach { screen ->

                    val imageVector = when (screen) {
                        is Screen.Home -> AppIcons.home
                        is Screen.Favorites -> AppIcons.favorites
                        is Screen.Profile -> AppIcons.profile
                        is Screen.Cart -> AppIcons.cart
                    }

                    BottomNavigationItem(
                        icon = {
                            Icon(
                                modifier = modifier.size(24.dp),
                                imageVector = imageVector,
                                contentDescription = ""
                            )
                        },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo = navController.graph.startDestination
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BottomAppBarButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String,
    selected: Boolean
) {
    IconButton(
        modifier = modifier,
        onClick = { /* doSomething() */ }
    ) {
        Column {
            Icon(
                imageVector = imageVector,
                modifier = Modifier.size(24.dp),
                contentDescription = contentDescription,
                tint = if (selected) {
                    MaterialTheme.colors.onPrimary
                } else {
                    bottomAppBarUnSelected
                }
            )
            // Text(contentDescription)
        }
    }
}

@Preview(PREVIEW_LIGHT_THEME, widthDp = PREVIEW_WIDTH, heightDp = PREVIEW_BOTTOM_NAVIGATION_HEIGHT)
@Composable
fun BottomBarLightThemePreview() {
    BottomBarPreview(darkTheme = false)
}

@Preview(PREVIEW_DARK_THEME, widthDp = PREVIEW_WIDTH, heightDp = PREVIEW_BOTTOM_NAVIGATION_HEIGHT)
@Composable
fun BottomBarDarkThemePreview() {
    BottomBarPreview(darkTheme = true)
}

@Composable
fun BottomBarPreview(darkTheme: Boolean) {
    MyTheme(darkTheme = darkTheme) {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    navController = rememberNavController(),
                    darkTheme = darkTheme
                )
            },
            content = {}
        )
    }
}
