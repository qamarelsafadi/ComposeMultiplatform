package com.qamar.composemultiplatform.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.unit.dp
import com.qamar.composemultiplatform.model.Properties


@Composable
internal fun CircularProgress(property: Properties) {
    val animatedProgress by animateFloatAsState(
        targetValue = property.percent,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    Box {
        CircularProgressIndicator(
            progress = 1f,
            color = LightGray,
            strokeWidth = 1.4.dp,
            modifier = Modifier.size(120.dp)

        )
        CircularProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(14.dp)),
            color = Color.Black,
            strokeWidth = 2.8.dp
        )

        PercentText( property, Modifier.Companion.align(Alignment.Center))
    }

}

@Composable
private fun PercentText(property: Properties, modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "${property.percent.times(100).toInt()}%",
            color = Color.Black
        )
        Text(
            text = property.propertyName,
            color = Color.Gray,
        )

    }
}