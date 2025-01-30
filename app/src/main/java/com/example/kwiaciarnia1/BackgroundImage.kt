package com.example.kwiaciarnia1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.blur
import androidx.compose.foundation.lazy.LazyListState

@Composable
fun BackgroundImage(listState: LazyListState) {
    val backgroundOffset by remember {
        derivedStateOf { listState.firstVisibleItemIndex * 70f + listState.firstVisibleItemScrollOffset / 5f }
    }

    val maxBackgroundOffset = -850f
    val przesuniecieObrazka = -100f

    Image(
        painter = painterResource(id = R.drawable.art_of_eden_background),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer(
                translationY = backgroundOffset.coerceIn(maxBackgroundOffset, 400f) + przesuniecieObrazka,
                scaleX = 1.4f,
                scaleY = 1.4f
            )
            .blur(3.dp)
    )
}