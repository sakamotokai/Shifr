package com.example.shifr.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.shifr.db.Modeldb
import com.example.shifr.retrofit.Model.MainResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryCard(content: Modeldb) {
    val context = LocalContext.current
/*    Scaffold(
        modifier = Modifier
            .border(color = MaterialTheme.colors.primary, width = 3.dp)
    ) {*/
        Column(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxSize(1f)
                .border(color = MaterialTheme.colors.primary, width = 3.dp)
                .padding(10.dp)
        ) {
            Row {
                if (content.brand != "")
                    Text(text = content.brand)
                if (content.scheme != "")
                    Text(text = content.scheme)
            }
            Row {
                if (content.type != "")
                    Text(text = content.type)
                //if (content.prepaid != false)
                    Text(text = if (content.prepaid) "Yes" else "No")
            }
            Row {
                if (content.countryName != "")
                    Text(text = content.countryName)
                if (content.countryAlpha2 != "")
                    Text(text = content.countryAlpha2)
            }
            Row {
                if (content.countryEmoji != "")
                    Text(text = content.countryEmoji)
                if (content.countryNumeric != "")
                    Text(text = content.countryNumeric)
            }
            Row {
                if (content.countryCurrency != "")
                    Text(text = content.countryCurrency)
                if (content.bankCity != "")
                    Text(
                        text = content.bankCity,
                        modifier = Modifier
                            .clickable {
                                val openMaps = Intent(Intent.ACTION_VIEW)
                                openMaps.data =
                                    Uri.parse("geo:${content.bankCity}")
                                openMaps.setPackage("com.google.android.apps.maps")
                                ContextCompat.startActivity(context, openMaps, null)
                            },
                        color = Color.Blue
                    )
            }
            Row {
                if (content.bankName != "")
                    Text(text = content.bankName)
                if (content.bankUrl != "")
                    Text(
                        text = content.bankUrl,
                        modifier = Modifier
                            .clickable {
                                val openURL = Intent(Intent.ACTION_VIEW)
                                openURL.data =
                                    Uri.parse("http://${content.bankUrl}")
                                ContextCompat.startActivity(context, openURL, null)
                            },
                        color = Color.Blue
                    )
            }
            Row {
                if (content.bankPhone != "")
                    Text(
                        text = content.bankPhone,
                        modifier = Modifier
                            .clickable {
                                val openNumber = Intent(Intent.ACTION_DIAL)
                                openNumber.data =
                                    Uri.parse("tel:${content.bankPhone}")
                                ContextCompat.startActivity(context, openNumber, null)
                            },
                        color = Color.Blue
                    )
                if (content.numberLength != 0)
                    Text(text = content.numberLength.toString())
            }
            Row {
                //if (content.numberLuhn != false)
                    Text(text = if (content.numberLuhn) "Yes" else "No")
            }
        }
    //}
}