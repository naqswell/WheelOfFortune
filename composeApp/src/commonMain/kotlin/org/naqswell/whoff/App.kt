package org.naqswell.whoff

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.naqswell.whoff.ui.theme.AppTheme
import org.naqswell.whoff.presentation.navigation.NavigationHost

@Composable
fun App() {
    AppTheme {
        Scaffold {
            val navController = rememberNavController()
            NavigationHost(navController = navController, modifier = Modifier.fillMaxSize())
        }

    }
}