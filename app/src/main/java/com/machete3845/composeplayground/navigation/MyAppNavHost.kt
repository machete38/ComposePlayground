package com.machete3845.composeplayground.navigation

import AnimationsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.machete3845.composeplayground.screens.DetailsScreen
import com.machete3845.composeplayground.screens.HomeScreen
import com.machete3845.composeplayground.screens.ProfileScreen

@Composable
fun MyAppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavRoutes.HOME
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavRoutes.HOME) {
            HomeScreen(
                onNavigateToDetails = { navController.navigate(NavRoutes.DETAILS) }
            )
        }
        composable(
            route = NavRoutes.DETAILS+"/{itemId}?name={name}",
            arguments = listOf(
                navArgument("itemId") { type = NavType.IntType },
                navArgument("name") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: 0
            val name = backStackEntry.arguments?.getString("name")
            DetailsScreen(
                onNavigateUp = { navController.navigateUp() },
                onNavigateToProfile = { navController.navigate(NavRoutes.PROFILE) },
                itemId = itemId,
                name = name
            )
        }
        composable(NavRoutes.PROFILE) {
            ProfileScreen(
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }

}