package com.example.shifr.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shifr.navigation.Route

@Composable
fun HistoryScreen(
    navController: NavHostController = rememberNavController()
){
    Text(text = "History", modifier = Modifier.fillMaxSize())
}