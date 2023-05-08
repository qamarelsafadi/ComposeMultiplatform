package com.qamar.composemultiplatform.components.plant

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qamar.composemultiplatform.model.Plant
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun PlantImage(currentItem: Plant, modifier: Modifier) {
    Crossfade(
        targetState = currentItem,
        modifier = modifier,
        animationSpec = tween(700)
    ) {
        Image(
            painterResource("Plants.png") ,
              contentDescription = "",
            Modifier.size(500.dp)

        )
    }
}