package com.example.shifr.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shifr.MainViewModel
import com.example.shifr.db.Modeldb
import com.example.shifr.globalDaodb
import com.example.shifr.navigation.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(LocalContext.current as ComponentActivity)
){
    viewModel.updateDataFromdb()
/*    val scope = rememberCoroutineScope()//CoroutineScope(SupervisorJob()+Dispatchers.IO)
    var content by remember{ mutableStateOf(listOf(Modeldb())) }
    val contentLiveData by viewModel.getAllModelLiveData().observeAsState()*/
    /*scope.launch {
        viewModel.getAllModel().collect{
            content = it
            Log.e("Log","---${content[0].bankName}---")
        }
    }*/
    Scaffold {
        LazyColumn{
            items(viewModel.flowSavedb.value!!){item->
                HistoryCard(content = item)
            }
        }
    }
}