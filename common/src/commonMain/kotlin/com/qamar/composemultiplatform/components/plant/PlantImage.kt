package com.qamar.composemultiplatform.components.plant

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qamar.composemultiplatform.LocalImage
import com.qamar.composemultiplatform.model.Plant

@Composable
internal fun PlantImage(currentItem: Plant, modifier: Modifier) {
    Crossfade(
        targetState = currentItem,
        modifier = modifier,
        animationSpec = tween(700)
    ) {
        LocalImage(imageResourceName = it.image!!,
              contentDescription = "",

        )
    }
}