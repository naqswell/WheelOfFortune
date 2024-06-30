package org.naqswell.whoff.presentation.screens.spin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.naqswell.whoff.presentation.screens.spin.components.SpinControllers
import org.naqswell.whoff.presentation.screens.spin.components.SpinLazyColumn
import org.naqswell.whoff.presentation.screens.spin.components.SpinWheelItemState
import org.naqswell.whoff.presentation.screens.spin.components.SpinWheelWithArrow
import org.naqswell.whoff.presentation.screens.spin.components.TextToShow
import org.naqswell.whoff.presentation.screens.spin.state.TextFieldState

@Composable
fun SpinContentMedium(
    isSpinWheelClickable: Boolean,
    spinWheelItems: List<SpinWheelItemState>,
    spinWheelRotation: Float,
    onLaunchSpinWheel: () -> Unit,
    selectionText: String,
    onAddNewTextField: () -> Unit,
    onClearState: () -> Unit,
    onPasteText: (text: String?) -> Unit,
    textFields: List<TextFieldState>,
    onTextChangeTextField: (index: Int, text: String) -> Unit,
    onVisibilityTextField: (index: Int) -> Unit,
    onRemoveTextField: (index: Int) -> Unit,
    onClearTextField: (index: Int) -> Unit,
) {
    Row(
        modifier = Modifier,
    ) {
        Column(
            modifier = Modifier.weight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            SpinWheelWithArrow(
                modifier = Modifier.weight(0.3f),
                sections = spinWheelItems,
                rotation = spinWheelRotation,
                onLaunch = { if (isSpinWheelClickable) onLaunchSpinWheel.invoke() },
            )

            TextToShow(
                modifier = Modifier
                    .widthIn(min = 400.dp, max = 400.dp)
                    .heightIn(min = 24.dp, max = 200.dp)
                    .padding(16.dp),
                animationTrigger = selectionText,
            )

            SpinControllers(
                onAddTextField = onAddNewTextField,
                onClearState = onClearState,
                onPasteText = { text: String? -> onPasteText.invoke(text) }
            )
        }

        SpinLazyColumn(
            modifier = Modifier.weight(0.5f),
            textFields = textFields,
            onTextChangeTextField = { index, text ->
                onTextChangeTextField.invoke(index, text)
            },
            onVisibilityTextField = { index -> onVisibilityTextField.invoke(index) },
            onRemoveTextField = { index -> onRemoveTextField.invoke(index) },
            onClearTextTextField = { index -> onClearTextField.invoke(index) },
        )
    }
}