package com.example.shifr.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shifr.MainViewModel
import com.example.shifr.screens.ContentScreen
import com.example.shifr.screens.HistoryScreen
import com.example.shifr.screens.MainScreen

@Composable
fun MainNavGraph(navController: NavHostController){
    val viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        LocalContext.current as ComponentActivity
    )
    NavHost(
        navController = navController,
        route = Route.Root.route,
    startDestination = Route.MainScreen.route){
        composable(route = Route.MainScreen.route){
            MainScreen(navController = navController)
        }

        composable(route = Route.HistoryScreen.route){
            HistoryScreen(navController = navController)
        }

        composable(route = Route.ContentScreen.route){
            ContentScreen(navController = navController)
        }
    }
}

sealed class Route(val route:String){
    object Root:Route("Root")
    object MainScreen:Route("Home")
    object HistoryScreen:Route("History")
    object ContentScreen:Route("Content")
}