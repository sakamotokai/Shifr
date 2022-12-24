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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shifr.Constants
import com.example.shifr.MainViewModel
import com.example.shifr.navigation.Route
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shifr.db.Modeldb
import com.example.shifr.globalDaodb
import com.example.shifr.retrofit.Model.MainResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response

@SuppressLint("CoroutineCreationDuringComposition", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = viewModel(LocalContext.current as ComponentActivity),
    navigateTo:()->Unit = {}
) {
    val scope = rememberCoroutineScope()
   // val data by globalDaodb.getAllModeldb().observeAsState(listOf())
    val scope2 = CoroutineScope(SupervisorJob()+Dispatchers.IO)
    var visible by remember{
        mutableStateOf(false)}

    Scaffold {
        val context = LocalContext.current
        var text by remember { mutableStateOf("") }
        var content:MainResponse? by remember{ mutableStateOf(null) }
        /*scope.launch {
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
        }*/
        Column {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                    //Constants.endOfApi = text
                })

            Button(onClick = {
                //viewModel.getRequastLiveData(Constants.endOfApi)
                //viewModel.getRequastLiveData(text)
                //viewModel.getRequast(text)
                viewModel.getRequastFlow(text)
                //сделать проверку на ошибку ибо нахрена нам вставлять данные, которые получили по дефолту
                //или ввели данные не от карты и пришла ошибка
                //viewModel.insert(convertResponseToModel(content?.body()!!))
                Log.e("Log",text)
            }) {
                Text(text = "Click on me")
            }
            scope.launch(Dispatchers.IO) {
                if(viewModel.flowResponse.value != null){
                    content = viewModel.listData
                }
                /*viewModel.flowResponse.collect{response->
                 if(response!=null) {
                     if(response.isSuccessful) {
                         content = response.body()
                         Log.e("Log", "items Go INSIDE")
                     }
                 }
                 Log.e("Log","items GO")
                }*/
            }
            //content = viewModel.flowResponse.value!!
            //viewModel.insert(convertResponseToModel(content))
            /*scope2.launch(Dispatchers.IO) {
                viewModel.flowResponse.collect{
                    Log.e("Log",it.scheme)
                    content = it
                }
            }*/

            if(content != null) {
                Text(text = content!!.type)
                Text(text = content!!.scheme)
                Text(text = content!!.brand)
                Text(text = content!!.country.name)
                Log.e("Log","-------------------------------")
            }
        }
    }
}

fun convertResponseToModel(response:MainResponse):Modeldb{
    return Modeldb(
        bankCity = response.bank.city,
        bankPhone = response.bank.phone,
        bankName = response.bank.name,
        bankUrl = response.bank.url,
        brand = response.brand,
        countryAlpha2 = response.country.alpha2,
        countryCurrency = response.country.currency,
        countryEmoji = response.country.emoji,
        countryLatitude = response.country.latitude,
        countryLongitude = response.country.longitude,
        countryName = response.country.name,
        countryNumeric = response.country.numeric,
        scheme = response.scheme,
        prepaid = response.prepaid,
        numberLength = response.number.length,
        numberLuhn = response.number.luhn,
        type = response.type
    )
}