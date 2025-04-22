package com.machete3845.composeplayground.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.machete3845.composeplayground.screens.DragAndDropScreen
import com.machete3845.composeplayground.screens.GesturesScreen
import com.machete3845.composeplayground.screens.MenuScreen

@Composable
fun NewFlowNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavRoutes.MENU
){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(NavRoutes.MENU) {
            MenuScreen(
                navigateToGesturesScreen = {
                    navController.navigate(NavRoutes.GESTURES)
                },
                navigateToDragAndDropScreen =
                    {
                        navController.navigate(NavRoutes.DRAG_AND_DROP)
                    }
            )
        }
        composable(NavRoutes.GESTURES) {
            GesturesScreen()
        }
        composable(NavRoutes.DRAG_AND_DROP) {
            DragAndDropScreen()
        }
    }
}