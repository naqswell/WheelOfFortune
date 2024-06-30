package org.naqswell.whoff.presentation.screens.spin

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.naqswell.whoff.presentation.screens.spin.components.SpinWheelItemState
import org.naqswell.whoff.presentation.screens.spin.state.TextFieldState
import kotlin.time.Duration.Companion.seconds

class SpinViewModel : ViewModel() {

    private val _textFields: SnapshotStateList<TextFieldState> =
        getInitialTextFields().toMutableStateList()
    val textFields: State<List<TextFieldState>> get() = derivedStateOf { _textFields.toList() }

    private val _selectionText: MutableState<String> = mutableStateOf("")
    val selectionText: State<String> get() = _selectionText

    private val _isSpinWheelClickable = mutableStateOf(true)
    val isSpinWheelClickable: State<Boolean> get() = _isSpinWheelClickable

    fun onItemSelectionStart(item: SpinWheelItemState) {
        _isSpinWheelClickable.value = false
        _selectionText.value = ""
    }

    fun onClickableChange() {
        _isSpinWheelClickable.value = !_isSpinWheelClickable.value
    }

    fun onItemSelectionEnd(item: SpinWheelItemState, index: Int) {
        viewModelScope.launch {
            _selectionText.value = item.textField.text
            delay(1.seconds)
            _isSpinWheelClickable.value = true
            _textFields.removeAt(index)
        }
    }

    fun onTextChangeTextField(index: Int, newText: String) {
        try {
            _textFields[index] = _textFields[index].copy(text = newText)
        } catch (_: Exception) {
            println("ERROR onVisibilityTextField wrong index")
        }
    }

    fun onAddNewTextField() {
        if (_isSpinWheelClickable.value) _textFields.add(TextFieldState())
    }

    fun onRemoveTextField(index: Int) {
        if (_isSpinWheelClickable.value && (_textFields.size > 2))
            _textFields.removeAt(index)
    }

    fun onClearTextField(idx: Int) {
        try {
            _textFields[idx] = _textFields[idx].copy(text = "")
        } catch (_: Exception) {
            println("ERROR onVisibilityTextField wrong index")
        }
    }

    fun onClearState() {
        _textFields.clear()
        _textFields.addAll(getInitialTextFields())
        _selectionText.value = ""
    }

    fun onPasteText(text: String?) {
        val rows = text?.split("\n") ?: return
        val newTextFields = mutableListOf<TextFieldState>()

        rows.forEach { row ->
            newTextFields.add(TextFieldState(text = row))
        }
        _textFields.clear()
        _textFields.addAll(newTextFields)
    }

    fun onVisibilityTextField(idx: Int) {
        try {
            val newFlag = !_textFields[idx].isVisible
            _textFields[idx] = _textFields[idx].copy(isVisible = newFlag)
        } catch (_: Exception) {
            println("ERROR onVisibilityTextField wrong index")
        }
    }

    private fun getInitialTextFields(): List<TextFieldState> {
        return listOf(
            TextFieldState(),
            TextFieldState()
        )
    }
}