package org.naqswell.whoff.presentation.screens.spin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.toPersistentList
import org.naqswell.whoff.presentation.screens.spin.components.SpinControllers
import org.naqswell.whoff.presentation.screens.spin.components.SpinLazyColumn
import org.naqswell.whoff.presentation.screens.spin.components.SpinWheelItemState
import org.naqswell.whoff.presentation.screens.spin.components.SpinWheelWithArrow
import org.naqswell.whoff.presentation.screens.spin.components.TextToShow
import org.naqswell.whoff.presentation.screens.spin.state.SpinWheelState
import org.naqswell.whoff.presentation.utils.koinViewModel
import org.naqswell.whoff.presentation.utils.screensize.UiType
import org.naqswell.whoff.presentation.utils.screensize.getUiType
import org.naqswell.whoff.presentation.utils.screensize.rememberScreenSizeInfo
import org.naqswell.whoff.ui.theme.selection_1
import org.naqswell.whoff.ui.theme.selection_2

@Composable
fun SpinScreen(
    modifier: Modifier = Modifier,
    viewModel: SpinViewModel = koinViewModel(),
) {
    val screenSizeInfo = rememberScreenSizeInfo()
    val uiType = screenSizeInfo.getUiType()

    SpinContainer(modifier = modifier) {
        val textFields by viewModel.textFields
        val coroutineScope = rememberCoroutineScope()

        val spinWheelState by remember(textFields) {
            mutableStateOf(
                SpinWheelState(
                    wheelItems = textFields.mapIndexed { index, textFieldHolder ->
                        val colors = if (index % 2 == 0) selection_1 else selection_2
                        SpinWheelItemState(
                            colors = colors.toPersistentList(),
                            textField = textFieldHolder,
                            content = {
                                Text(
                                    modifier = Modifier
                                        .width(120.dp)
                                        .padding(8.dp)
                                        .rotate(-90f),
                                    text = textFieldHolder.text,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    textAlign = TextAlign.Start,
                                    color = Color.Magenta
                                )
                            },
                        )
                    },
                    onItemSelectionStart = { item: SpinWheelItemState ->
                        viewModel.onItemSelectionStart(item)
                    },
                    onItemSelectionEnd = { item: SpinWheelItemState, index: Int ->
                        viewModel.onItemSelectionEnd(item = item, index = index)
                    },
                )
            )
        }
        val isSpinWheelClickable by viewModel.isSpinWheelClickable
        val selectionText by viewModel.selectionText

        when (uiType) {
            UiType.COMPACT ->
                SpinContentCompact(
                    isSpinWheelClickable = isSpinWheelClickable,
                    spinWheelItems = spinWheelState.wheelItems,
                    spinWheelRotation = spinWheelState.rotation.value,
                    onLaunchSpinWheel = {
                        if (isSpinWheelClickable) spinWheelState.onLaunch(coroutineScope)
                    },
                    selectionText = selectionText,
                    onAddNewTextField = viewModel::onAddNewTextField,
                    onClearState = viewModel::onClearState,
                    onPasteText = { text: String? -> viewModel.onPasteText(text) },
                    textFields = textFields,
                    onTextChangeTextField = { index, text ->
                        viewModel.onTextChangeTextField(index, text)
                    },
                    onVisibilityTextField = { index -> viewModel.onVisibilityTextField(index) },
                    onRemoveTextField = { index -> viewModel.onRemoveTextField(index) },
                    onClearTextField = { index -> viewModel.onClearTextField(index) }
                )

            UiType.MEDIUM, UiType.EXPANDED ->
                SpinContentMedium(
                    isSpinWheelClickable = isSpinWheelClickable,
                    spinWheelItems = spinWheelState.wheelItems,
                    spinWheelRotation = spinWheelState.rotation.value,
                    onLaunchSpinWheel = {
                        if (isSpinWheelClickable) spinWheelState.onLaunch(coroutineScope)
                    },
                    selectionText = selectionText,
                    onAddNewTextField = viewModel::onAddNewTextField,
                    onClearState = viewModel::onClearState,
                    onPasteText = { text: String? -> viewModel.onPasteText(text) },
                    textFields = textFields,
                    onTextChangeTextField = { index, text ->
                        viewModel.onTextChangeTextField(index, text)
                    },
                    onVisibilityTextField = { index -> viewModel.onVisibilityTextField(index) },
                    onRemoveTextField = { index -> viewModel.onRemoveTextField(index) },
                    onClearTextField = { index -> viewModel.onClearTextField(index) }
                )
        }
    }
}


@Composable
private fun SpinContainer(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        content()
    }
}