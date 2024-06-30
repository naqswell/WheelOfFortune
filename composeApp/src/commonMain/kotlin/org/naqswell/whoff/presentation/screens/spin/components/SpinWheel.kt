package org.naqswell.whoff.presentation.screens.spin.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.min

@Composable
internal fun SpinWheel(
    modifier: Modifier = Modifier,
    sections: List<SpinWheelItemState>,
) {
    BoxWithConstraints(
        modifier = modifier
    ) {

        val degreesPerItems = sections.getDegreesPerItem()
        val size = min(this.maxHeight, this.maxWidth)

        sections.forEachIndexed { index, item ->
            SpinWheelSlice(
                modifier = Modifier.rotate(degrees = degreesPerItems * index),
                size = size,
                brush = Brush.sweepGradient(item.colors),
                degree = degreesPerItems,
                content = {
                    AnimatedVisibility(item.textField.isVisible) {
                        item.content(index.toString())
                    }
                }
            )
        }
    }
}