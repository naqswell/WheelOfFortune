package org.naqswell.whoff

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.naqswell.whoff.di.KoinInitializer
import java.awt.Dimension

fun main() = application {
    KoinInitializer().init()
    Window(
        onCloseRequest = ::exitApplication,
        title = "WhOff\uD83D\uDC36",
    ) {
        window.minimumSize = Dimension(300, 300)
        App()
    }
}