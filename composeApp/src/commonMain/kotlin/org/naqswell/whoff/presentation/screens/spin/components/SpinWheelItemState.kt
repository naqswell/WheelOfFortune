package org.naqswell.whoff.presentation.screens.spin.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.PersistentList
import org.naqswell.whoff.presentation.screens.spin.state.TextFieldState

@Stable
data class SpinWheelItemState(
    val colors: PersistentList<Color>,
    val textField: TextFieldState = TextFieldState(),
    val content: @Composable (text: String) -> Unit,
)

internal fun List<SpinWheelItemState>.getDegreesPerItem(): Float = 360f / this.size.toFloat()