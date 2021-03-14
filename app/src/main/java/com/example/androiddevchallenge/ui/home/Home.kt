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
package com.example.androiddevchallenge.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.CardItem
import com.example.androiddevchallenge.model.ImageItem
import com.example.androiddevchallenge.ui.bottombar.BottomAppBar
import com.example.androiddevchallenge.ui.checkbox.CustomCheckbox
import com.example.androiddevchallenge.ui.checkbox.CutomCheckboxDefaults
import com.example.androiddevchallenge.ui.theme.AppIcons
import com.example.androiddevchallenge.ui.theme.Elevation
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.getCardBackgroundColor
import com.example.androiddevchallenge.ui.theme.getCheckBoxBackgroundColor
import com.example.androiddevchallenge.ui.theme.getCheckForeground
import com.example.androiddevchallenge.ui.theme.lightGray

@Composable
fun Home(
    modifier: Modifier = Modifier,
    navController: NavController,
    darkTheme: Boolean = isSystemInDarkTheme()
) {

    val cardItems = getCardItems()
    val imageItems = getImageItems()

    MyTheme(darkTheme = darkTheme) {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    navController = navController,
                    darkTheme = darkTheme
                )
            }
        ) { innerPadding ->
            modifier.padding(innerPadding)

            HomeContent(
                modifier = modifier,
                cardRows = {
                    CardRows(
                        modifier = modifier,
                        cardItems = cardItems,
                        cardBackground = getCardBackgroundColor(darkTheme)
                    )
                },
                imageList = {
                    ImageList(
                        modifier = modifier,
                        imageItems = imageItems,
                        checkBoxBackground = getCheckBoxBackgroundColor(darkTheme),
                        checkBoxForeground = getCheckForeground(darkTheme)
                    )
                }
            )
        }
    }
}

fun getCardItems() = listOf(
    CardItem(text = "Desert chic", resourceId = R.drawable.desert_chic),
    CardItem(text = "Tiny terrariums", resourceId = R.drawable.tiny_terrariums),
    CardItem(text = "Jungle vibes", resourceId = R.drawable.jungle_vibes),
    CardItem(text = "Easy care", resourceId = R.drawable.easy_care),
    CardItem(text = "Statements", resourceId = R.drawable.statements),
)

fun getImageItems() = listOf(
    ImageItem(caption = "Monstera", resourceId = R.drawable.monstera, checked = true),
    ImageItem(caption = "Aglaonema", resourceId = R.drawable.aglaonema),
    ImageItem(caption = "Peace Lilly", resourceId = R.drawable.peace_lilly),
    ImageItem(caption = "Fiddle Leaf", resourceId = R.drawable.fiddle_leaf),
    ImageItem(caption = "Snake plant", resourceId = R.drawable.snake_plant),
    ImageItem(caption = "Pothos", resourceId = R.drawable.pothos),
)

@Composable
fun ImageList(
    modifier: Modifier = Modifier,
    imageItems: List<ImageItem>,
    checkBoxBackground: Color,
    checkBoxForeground: Color
) {

    val itemHeight = 64.dp

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        imageItems.forEach { imageItem ->
            Row(
                modifier = modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            ) {
                Image(
                    modifier = modifier
                        .width(itemHeight)
                        .height(itemHeight)
                        .clip(MaterialTheme.shapes.small),
                    painter = painterResource(id = imageItem.resourceId),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )

                Spacer(
                    modifier = modifier
                        .width(8.dp)
                )

                Column(
                    modifier = modifier
                        .height(itemHeight)
                    // .weight(1f)
                ) {
                    Row(
                        modifier = modifier
                            .weight(1f),
                    ) {
                        Column(
                            modifier = modifier
                                .padding(start = 8.dp)
                                .weight(1f)
                        ) {
                            Text(
                                modifier = modifier
                                    .paddingFromBaseline(top = 24.dp),
                                text = imageItem.caption,
                                style = MaterialTheme.typography.h2,
                                color = MaterialTheme.colors.onPrimary
                            )
                            Text(
                                modifier = modifier
                                    .paddingFromBaseline(bottom = 24.dp),
                                text = imageItem.description,
                                style = MaterialTheme.typography.body1,
                                color = MaterialTheme.colors.onPrimary
                            )
                        }
                        ImageCheckbox(
                            modifier = modifier,
                            initialChecked = imageItem.checked,
                            checkBoxBackground = checkBoxBackground,
                            checkBoxForeground = checkBoxForeground
                        )
                    }

                    Divider(
                        color = lightGray,
                        thickness = 1.dp
                    )
                }
            }
        }
    }
}

@Composable
fun ImageCheckbox(
    modifier: Modifier = Modifier,
    initialChecked: Boolean,
    checkBoxBackground: Color,
    checkBoxForeground: Color
) {
    var checked by remember { mutableStateOf(initialChecked) }

    Row(
        modifier = modifier
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = modifier
                .size(24.dp)
                .border(
                    width = 1.dp,
                    color = lightGray,
                    shape = MaterialTheme.shapes.small
                ), // .border(width = 1.dp, color = lightGray)
            shape = MaterialTheme.shapes.small,
            color = lightGray
        ) {
            CustomCheckbox(
                checked = checked,
                onCheckedChange = { checked = it },
                modifier = modifier
                    .size(24.dp),
                colors = CutomCheckboxDefaults.colors(
                    checkedColor = checkBoxBackground,
                    uncheckedColor = MaterialTheme.colors.background,
                    checkmarkColor = checkBoxForeground,
                    disabledColor = MaterialTheme.colors.background,
                    disabledIndeterminateColor = MaterialTheme.colors.background
                ),
                radiusSize = 0.dp
            )
        }
    }
}

@Composable
fun CardRows(
    modifier: Modifier = Modifier,
    cardItems: List<CardItem>,
    cardBackground: Color
) {

    // LazyRow(
    //     modifier = modifier
    //         .padding(start = 16.dp)
    // ) {
    //     items(cardItems) { cardItem ->
    //         Column(
    //             modifier = modifier
    //                 .clip(shape = MaterialTheme.shapes.small)
    //                 .size(136.dp)
    //                 .background(getCardBackgroundColor(darkTheme = darkTheme))
    //         ) {
    //             Image(
    //                 modifier = modifier
    //                     .width(136.dp)
    //                     .height(96.dp)
    //                 ,
    //                 painter = painterResource(id = cardItem.resourceId),
    //                 contentDescription = "",
    //                 contentScale = ContentScale.Crop
    //             )
    //             Text(
    //                 modifier = modifier
    //                     .height(40.dp),
    //                 text = cardItem.text
    //             )
    //         }
    //         Spacer(modifier = modifier
    //             .width(8.dp)
    //         )
    //     }
    // }

    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
    ) {
        Spacer(
            modifier = modifier
                .width(16.dp)
        )

        cardItems.forEach { cardItem ->
            Column {
                Card(
                    modifier = modifier,
                    shape = MaterialTheme.shapes.small,
                    backgroundColor = cardBackground,
                    elevation = Elevation.card
                ) {
                    Column {
                        Image(
                            modifier = modifier
                                .width(136.dp)
                                .height(96.dp),
                            painter = painterResource(id = cardItem.resourceId),
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            modifier = modifier
                                .height(40.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                modifier = modifier
                                    .padding(start = 16.dp),
                                text = cardItem.text,
                                style = MaterialTheme.typography.h2,
                                color = MaterialTheme.colors.onPrimary
                            )
                        }
                    }
                }
                // for Elevation
                Spacer(modifier = modifier.height(8.dp))
            }

            Spacer(
                modifier = modifier
                    .width(8.dp)
            )
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    cardRows: @Composable ColumnScope.() -> Unit,
    imageList: @Composable ColumnScope.() -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        Spacer(Modifier.height(40.dp))

        InputSearchText(
            modifier = modifier,
        )

        Text(
            modifier = modifier
                .paddingFromBaseline(top = 32.dp)
                .padding(start = 16.dp, bottom = 16.dp),
            text = "Browse themes",
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onPrimary
        )

        cardRows()

        Row {
            Text(
                modifier = modifier
                    .weight(1f)
                    .paddingFromBaseline(top = 32.dp, bottom = 16.dp)
                    .padding(start = 16.dp),
                text = "Design your home garden",
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onPrimary
            )
            Row(
                modifier = modifier
                    .height(48.dp)
                    .padding(end = 16.dp, bottom = 8.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Icon(
                    imageVector = AppIcons.filterList,
                    contentDescription = "",
                    modifier = modifier
                        .size(24.dp),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
        imageList()
    }
}

@Composable
fun InputSearchText(
    modifier: Modifier = Modifier,
    placeholder: String = ""
) {
    var text by remember { mutableStateOf("") }

    Box(
        modifier = modifier.padding(start = 16.dp, end = 16.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(1.dp, lightGray, MaterialTheme.shapes.small),
            value = text,
            onValueChange = { text = it },
            singleLine = true,
            textStyle = TextStyle(
                fontStyle = MaterialTheme.typography.body1.fontStyle,
                color = MaterialTheme.colors.onPrimary
            ),
            decorationBox = { innerTextField ->
                Column(
                    modifier = modifier.padding(start = 16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    innerTextField()
                }
            }
        )

        if (text.isEmpty()) {

            Row {
                Icon(
                    imageVector = AppIcons.search,
                    contentDescription = "",
                    modifier = modifier
                        .height(18.dp)
                        .padding(start = 16.dp),
                    tint = MaterialTheme.colors.onPrimary
                )
                Text(
                    modifier = modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth(),
                    text = "Search",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun HomeLightPreview() {
    HomePreview(darkTheme = false)
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun HomeDarkPreview() {
    HomePreview(darkTheme = true)
}

@Composable
fun HomePreview(darkTheme: Boolean = false) {
    Home(
        navController = rememberNavController(),
        darkTheme = darkTheme
    )
}
