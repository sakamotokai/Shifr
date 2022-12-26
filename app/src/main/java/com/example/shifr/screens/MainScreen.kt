package com.example.shifr.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shifr.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shifr.db.Modeldb
import com.example.shifr.retrofit.Model.MainResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = viewModel(LocalContext.current as ComponentActivity),
    navigateTo: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    // val data by globalDaodb.getAllModeldb().observeAsState(listOf())
    val scope2 = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    var visible by remember {
        mutableStateOf(false)
    }

    var checkState by remember { mutableStateOf(false) }

    Scaffold {
        val context = LocalContext.current
        var text by remember { mutableStateOf("") }
        var content: MainResponse? by remember { mutableStateOf(null) }

        Column {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                    //Constants.endOfApi = text
                })

            Button(onClick = {
                viewModel.getRequastInsertToRoom(text)
                Log.e("Log", text)
                scope.launch {
                    viewModel.flowResponse.collect { response ->
                        if (response != null) {
                            if (response.isSuccessful) {
                                content = response.body()
                                Log.e("Log", "items Go INSIDE")
                                checkState = true
                            }
                        }
                    }
                }
            }) {
                Text(text = "Click on me")
            }

            if (content != null)
                LazyColumn {
                    if (content!!.type != null)
                        item {
                            Text(text = content!!.type!!)
                        }
                    if (content!!.scheme != null)
                        item {
                            Text(text = content!!.scheme!!)
                        }
                    if (content!!.bank?.url != null)
                        item {
                            Text(
                                text = content!!.bank?.url!!,
                                modifier = Modifier
                                    .clickable {
                                        val openURL = Intent(Intent.ACTION_VIEW)
                                        openURL.data =
                                            Uri.parse("http://${content!!.bank?.url!!}")
                                        startActivity(context, openURL, null)
                                    },
                                color = Color.Blue
                            )
                        }
                    if (content!!.bank?.phone != null)
                        item {
                            Text(
                                text = content!!.bank?.phone!!,
                                modifier = Modifier
                                    .clickable {
                                        val openNumber = Intent(Intent.ACTION_DIAL)
                                        openNumber.data =
                                            Uri.parse("tel:${content!!.bank?.phone!!}")
                                        startActivity(context, openNumber, null)
                                    },
                                color = Color.Blue
                            )
                        }
                    if (content!!.bank?.name != null)
                        item {
                            Text(text = content!!.bank?.name!!)
                        }
                    if (content!!.bank?.city != null)
                        item {
                            Text(
                                text = content!!.bank?.city!!,
                                modifier = Modifier
                                    .clickable {
                                        val openMaps = Intent(Intent.ACTION_VIEW)
                                        openMaps.data =
                                            Uri.parse("geo:${content!!.bank?.city!!}")
                                        openMaps.setPackage("com.google.android.apps.maps")
                                        startActivity(context, openMaps, null)
                                    },
                                color = Color.Blue
                            )
                        }
                    if (content!!.brand != null)
                        item {
                            Text(text = content!!.brand!!)
                        }
                    if (content!!.country?.name != null)
                        item {
                            Text(text = content!!.country?.name!!)
                        }
                    if (content!!.country?.emoji != null)
                        item {
                            Text(text = content!!.country?.emoji!!)
                        }
                    if (content!!.country?.alpha2 != null)
                        item {
                            Text(text = content!!.country?.alpha2!!)
                        }
                    if (content!!.country?.currency != null)
                        item {
                            Text(text = content!!.country?.currency!!)
                        }
                    if (content!!.country?.latitude != null)
                        item {
                            Text(text = content!!.country?.currency!!)
                        }
                    if (content!!.number?.luhn != null)
                        item {
                            Text(text = if (content!!.number?.luhn!!) "Yes" else "No")
                        }
                    if (content!!.number?.length != null)
                        item {
                            Text(text = content!!.number?.length!!.toString())
                        }
                }
        }
    }
}

fun convertResponseToModel(response: MainResponse): Modeldb {
    return Modeldb(
        bankCity = if (response.bank?.city != null) response.bank.city else "",
        bankPhone = if (response.bank?.phone != null) response.bank.phone else "",
        bankName = if (response.bank?.name != null) response.bank.name else "",
        bankUrl = if (response.bank?.url != null) response.bank.url else "",
        brand = if (response.brand != null) response.brand else "",
        countryAlpha2 = if (response.country?.alpha2 != null) response.country.alpha2 else "",
        countryCurrency = if (response.country?.currency != null) response.country.currency else "",
        countryEmoji = if (response.country?.emoji != null) response.country.emoji else "",
        countryLatitude = if (response.country?.latitude != null) response.country.latitude else 0,
        countryLongitude = if (response.country?.longitude != null) response.country.longitude else 0,
        countryName = if (response.country?.name != null) response.country.name else "",
        countryNumeric = if (response.country?.numeric != null) response.country.numeric else "",
        scheme = if (response.scheme != null) response.scheme else "",
        prepaid = if (response.prepaid != null) response.prepaid else false,
        numberLength = if (response.number?.length != null) response.number.length else 0,
        numberLuhn = if (response.number?.luhn != null) response.number.luhn else false,
        type = if (response.type != null) response.type else ""
    )
}