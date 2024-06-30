package org.naqswell.whoff

import androidx.compose.ui.window.ComposeUIViewController
import org.naqswell.whoff.di.KoinInitializer

@Suppress(names = ["Unused", "FunctionName"])
fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) {
    App()
}