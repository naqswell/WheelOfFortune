package org.naqswell.whoff.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import org.naqswell.whoff.presentation.screens.spin.SpinViewModel

actual val viewModelModule = module {
    viewModelOf(::SpinViewModel)
}