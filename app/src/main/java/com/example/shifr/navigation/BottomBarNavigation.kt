package com.example.shifr.navigation

import androidx.activity.ComponentActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shifr.MainViewModel
import com.example.shifr.ui.theme.Purple40
import com.example.shifr.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarNavigation(
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = viewModel(
        LocalContext.current as ComponentActivity
    )
){
    var tabPage by remember { mutableStateOf(TabPage.HOME) }
    val backgroundColor by animateColorAsState(if (tabPage == TabPage.HOME) Purple40 else Purple80)
    Scaffold(
        bottomBar = {
            HomeTabBar(
                navController = navController,
                backgroundColor = backgroundColor,
                onTabSelected = {tabPage = it},
                tabPage = tabPage)
        }
    ) {
        MainNavGraph(navController = navController)
    }
}

/*
sealed class MainBottomBar(
    var route: String,
    var icon: ImageVector
) {
    object Home:MainBottomBar(
        route = Route.MainScreen.route,
        icon = Icons.Filled.Home
    )

    object History:MainBottomBar(
        route = Route.HistoryScreen.route,
        icon = Icons.Filled.List
    )
}*/
