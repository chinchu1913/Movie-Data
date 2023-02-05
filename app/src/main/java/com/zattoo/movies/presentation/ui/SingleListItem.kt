package com.zattoo.movies.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.zattoo.movies.ui.theme.CardViewColor
import com.zattoo.movies.ui.theme.TextColor


@Composable
fun SingleListItem(title: String, subtitle1: String, subtitle2: String, imageUrl: String) {
    Card(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)) {
        Box(
            modifier = Modifier
                .background(CardViewColor)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Card(modifier = Modifier) {
                    Box(
                        modifier = Modifier
                            .size(82.dp)
                    ) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier, verticalArrangement = Arrangement.Center) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.subtitle1,
                        color = TextColor
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = subtitle1, style = MaterialTheme.typography.subtitle2)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = subtitle2, style = MaterialTheme.typography.subtitle2)
                }
            }
        }
    }
}