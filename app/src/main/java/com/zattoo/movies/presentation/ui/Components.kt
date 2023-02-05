package com.zattoo.movies.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zattoo.movies.R
import com.zattoo.movies.ui.theme.*

@Composable
fun NotConnectedComponent() {
    Box(
        modifier = Modifier
            .background(color = ColorStatusNotConnected)
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(
                id = R.string.text_no_connectivity
            ),
            color = White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun ConnectedComponent() {
    Box(
        modifier = Modifier
            .background(color = ColorStatusConnected)
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(
                id = R.string.text_connectivity
            ),
            color = White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}


/*
Custom composable for the app bar
 */
@Composable
fun AppBarComponent(
    title: String,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = ToolbarColor,
        elevation = 2.dp
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            title, textAlign = TextAlign.Start,
            fontSize = 16.sp,
            color = ToolbarTextColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ErrorComponent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            stringResource(id = R.string.text_error_text),
            style = MaterialTheme.typography.subtitle1.copy(fontSize = 14.sp),
            textAlign = TextAlign.Center
        )
    }
}