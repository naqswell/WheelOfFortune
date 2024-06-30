package org.naqswell.whoff.presentation.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.naqswell.whoff.presentation.screens.spin.SpinScreen

object Route {
    const val SPIN = "SpinScreen"
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.SPIN
    ) {
        val paddingModifier = modifier
            .statusBarsPadding()
            .navigationBarsPadding()

        spinScreen(navController = navController, modifier = paddingModifier)
    }
}

private fun NavGraphBuilder.spinScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    composable(Route.SPIN) {
        SpinScreen(modifier = modifier)
    }
}