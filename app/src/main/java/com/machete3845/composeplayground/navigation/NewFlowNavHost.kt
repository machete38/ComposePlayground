package com.machete3845.composeplayground.navigation

import AnimationsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.machete3845.composecanvas.CanvasScreen
import com.machete3845.composeplayground.screens.DragAndDropScreen
import com.machete3845.composeplayground.screens.GesturesScreen
import com.machete3845.composeplayground.screens.MenuScreen
import com.machete3845.composeplayground.screens.ModifiersScreen
import com.machete3845.composeplayground.screens.MultiTouchScreen

@Composable
fun NewFlowNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavRoutes.MENU
) {
    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        composable(NavRoutes.MENU) {
            MenuScreen(navigateToGesturesScreen = {
                navController.navigate(NavRoutes.GESTURES)
            }, navigateToDragAndDropScreen = {
                navController.navigate(NavRoutes.DRAG_AND_DROP)
            }, navigateToMultiTouchScreen = {
                navController.navigate(NavRoutes.MULTI_TOUCH)
            }, navigateToAnimationScreen = {
                navController.navigate(NavRoutes.ANIMATIONS)
            }, navigateToModifiersScreen = {
                navController.navigate(NavRoutes.MODIFIERS)
            }, navigateToCanvasScreen = {
                navController.navigate(NavRoutes.CANVAS)
            })
        }
        composable(NavRoutes.GESTURES) {
            GesturesScreen()
        }
        composable(NavRoutes.DRAG_AND_DROP) {
            DragAndDropScreen()
        }
        composable(NavRoutes.MULTI_TOUCH) {
            MultiTouchScreen()
        }
        composable(NavRoutes.MULTI_TOUCH) {
            MultiTouchScreen()
        }
        composable(NavRoutes.ANIMATIONS) {
            AnimationsScreen()
        }
        composable(NavRoutes.MODIFIERS) {
            ModifiersScreen()
        }
        composable(NavRoutes.CANVAS) {
            CanvasScreen()
        }
    }
}