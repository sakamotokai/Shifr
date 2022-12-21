package com.example.shifr.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shifr.Constants
import com.example.shifr.MainViewModel
import com.example.shifr.navigation.Route
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shifr.globalDaodb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
   // val data by globalDaodb.getAllModeldb().observeAsState(listOf())
    val scope = rememberCoroutineScope()//CoroutineScope(SupervisorJob()+Dispatchers.Default)
    Scaffold {
        val context = LocalContext.current
        var text by remember { mutableStateOf("") }
        scope.launch {
            viewModel.flowResponse.collect{state->
                state?.let { response->
                    if(response.isSuccessful){
                        Log.e("Log",response.body()!!.type)
                        Toast.makeText(context,response.body()?.type,Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context,"ERROR",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        Column {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                    Constants.endOfApi = text
                })

            Button(onClick = {
                viewModel.getRequast("45717360")
                //navController.navigate(Route.ContentScreen.route)
            }) {
                Text(text = "Click on me")
            }
        }
    }
}