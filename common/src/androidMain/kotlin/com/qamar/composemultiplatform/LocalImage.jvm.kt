package com.qamar.composemultiplatform // ktlint-disable filename

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

// Use of the function getIdentifier is discouraged, but we need to use it since the drawable names are defined in the common code for both
// platforms and on each platform we need to get the drawable according to provided name.
@OptIn(ExperimentalResourceApi::class)
@SuppressLint("ComposableNaming", "DiscouragedApi")
@Composable
internal actual fun __LocalImage(imageResourceName: String, modifier: Modifier, contentDescription: String?) {
    Image(
        painter = painterResource(imageResourceName),
        contentDescription = null,
        modifier = modifier,)
}
