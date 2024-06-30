package org.naqswell.whoff.presentation.screens.spin.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun SpinWheelWithArrow(
    modifier: Modifier = Modifier,
    sections: List<SpinWheelItemState>,
    rotation: Float,
    onLaunch: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier,
            imageVector = Icons.Filled.ArrowDownward,
            contentDescription = null,
            tint = Color.Magenta
        )
        Card(
            modifier = Modifier
                .aspectRatio(1f)
                .padding(16.dp)
                .clip(CircleShape)
                .clickable { onLaunch.invoke() }
        ) {
            SpinWheel(
                modifier = modifier
                    .fillMaxSize()
                    .graphicsLayer { rotationZ = rotation },
                sections = sections,
            )
        }
    }
}