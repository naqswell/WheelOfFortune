package org.naqswell.whoff.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.naqswell.whoff.presentation.screens.spin.SpinViewModel

val viewModelModuleDefault = module {
    singleOf(::SpinViewModel)
}