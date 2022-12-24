package com.example.shifr.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shifr.Constants
import com.example.shifr.MainViewModel
import com.example.shifr.Repository
import com.example.shifr.retrofit.Model.MainResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import retrofit2.Response

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    val context = LocalContext.current

    Scaffold {
        Column {
            //viewModel.getRequast(Constants.endOfApi)
            Button(onClick = {
                /*viewModel.getRequast(Constants.endOfApi)*/
                Log.e("Log", Constants.endOfApi)
                //viewModel.getRequast(Constants.endOfApi)
                /*scope.launch {
                    val response = Repository().getPost(Constants.endOfApi)
                    if(response.isSuccessful){
                        Log.e("Log",response.body()!!.scheme)
                    }
                }*/
                /*scope.launch {
                    delay(1000)
                    viewModel.flowResponse.collect { response ->
                        response?.let {
                            if (response.isSuccessful) {
                                Log.e("Log", response.body()!!.brand)
                                Log.e("Log", response.body()!!.scheme)
                                Log.e("Log", response.body()!!.type)
                                Log.e("Log", response.body()!!.prepaid.toString())
                                Log.e("Log", response.body()!!.bank.city)
                                Log.e("Log", response.body()!!.bank.name)
                                Log.e("Log", response.body()!!.bank.url)
                            }
                        }
                    }
                }*/
            }) {
                Text(text = "Click on me")
            }

        }
    }
}