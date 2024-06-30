package org.naqswell.whoff.presentation.screens.spin.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.naqswell.whoff.presentation.screens.spin.state.TextFieldState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SpinLazyColumn(
    modifier: Modifier = Modifier,
    textFields: List<TextFieldState>,
    onTextChangeTextField: (index: Int, text: String) -> Unit,
    onVisibilityTextField: (index: Int) -> Unit,
    onRemoveTextField: (index: Int) -> Unit,
    onClearTextTextField: (index: Int) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(8.dp)
        ),
        contentPadding = PaddingValues(8.dp),
    ) {
        itemsIndexed(items = textFields, key = { _, item -> item.uuid }) { index, item ->
            Row(
                modifier = Modifier.animateItemPlacement(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedVisibility(textFields.size > 2) {
                    IconButton(
                        modifier = Modifier.padding(top = 4.dp),
                        onClick = { onRemoveTextField.invoke(index) }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.RemoveCircle,
                            contentDescription = "Remove"
                        )
                    }
                }

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    value = item.text,
                    label = { Text(text = (index + 1).toString()) },
                    onValueChange = { text -> onTextChangeTextField.invoke(index, text) },
                    leadingIcon = {
                        IconButton(
                            onClick = { onVisibilityTextField.invoke(index) }
                        ) {
                            Icon(
                                imageVector = if (item.isVisible) {
                                    Icons.Filled.Visibility
                                } else Icons.Filled.VisibilityOff,
                                contentDescription = "Visibility Icon"
                            )
                        }
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = { onClearTextTextField.invoke(index) }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "Clear Icon"
                            )
                        }
                    },
                    visualTransformation = if (item.isVisible) {
                        VisualTransformation.None
                    } else PasswordVisualTransformation()
                )
            }
        }
    }
}