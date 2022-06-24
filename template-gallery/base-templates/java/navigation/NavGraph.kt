package {{applicationId}}.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost

@ExperimentalMaterialApi
@Composable
fun SetupNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        route = ROOT_ROUTE,
    ) {
        setupHomeNavGraph(navController = navController)
        // navsetup
    }
}