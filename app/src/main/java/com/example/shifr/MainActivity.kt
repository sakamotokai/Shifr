package com.example.shifr

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.shifr.navigation.BottomBarNavigation
import com.example.shifr.ui.theme.ShifrTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShifrTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    startFun()
                    //val scope = CoroutineScope(SupervisorJob()+Dispatchers.Default)
                    //val viewModelFactory = MainViewModelFactory(Repository())
                    val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
                    //mainViewModel.getRequast()
                    BottomBarNavigation()
                    /*mainViewModel.myResponse.observe(this, Observer { response->
                        if(response.isSuccessful){
                            response.body()?.let {
                                Log.e("Log",it.scheme)}
                            } else{
                                Log.e("Log",response.code().toString())
                            }
                    })*/
                }
            }
        }
    }
    @Composable
    fun startFun(){}

}