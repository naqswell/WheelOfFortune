package org.naqswell.whoff.presentation.screens.spin.state

import androidx.compose.runtime.Stable
import org.naqswell.whoff.utils.randomUUID

@Stable
data class TextFieldState(
    val uuid: String = randomUUID(),
    val formField: FormField = FormField(),
    val text: String = "",
    val isVisible: Boolean = false
)

@Stable
data class FormField(
    val label: String = "Enter your data",
)