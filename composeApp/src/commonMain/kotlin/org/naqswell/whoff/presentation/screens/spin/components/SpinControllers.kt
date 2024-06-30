package org.naqswell.whoff.presentation.screens.spin.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Replay
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager

@Composable
fun SpinControllers(
    modifier: Modifier = Modifier,
    onAddTextField: () -> Unit,
    onClearState: () -> Unit,
    onPasteText: (text: String?) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedIconButton(
            modifier = Modifier,
            onClick = { onClearState.invoke() }
        ) {
            Icon(Icons.Outlined.Replay, contentDescription = "Spin")
        }

        OutlinedIconButton(
            modifier = Modifier,
            onClick = { onAddTextField.invoke() }
        ) {
            Icon(Icons.Outlined.Add, contentDescription = "Add")
        }

        val clipboard: ClipboardManager = LocalClipboardManager.current
        OutlinedIconButton(
            modifier = Modifier,
            onClick = { onPasteText.invoke(clipboard.getText()?.text) }
        ) {
            Icon(Icons.Filled.ContentPaste, contentDescription = "Visibility Icon")
        }
    }
}