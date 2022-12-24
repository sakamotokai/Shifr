package com.example.shifr.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shifr.db.Modeldb
import com.example.shifr.retrofit.Model.MainResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryCard(content: Modeldb) {
    Scaffold(
        modifier = Modifier
            .height(200.dp)
            .border(color = MaterialTheme.colors.primary, width = 3.dp)
    ) {
        Column {
            Row {
                Text(text = content.brand)
                Text(text = content.scheme)
            }
            Row {
                Text(text = content.type)
                Text(text = if (content.prepaid) "Yes" else "No")
            }
            Row {
                Text(text = content.countryName)
                Text(text = content.countryAlpha2)
            }
            Row {
                Text(text = content.countryEmoji)
                Text(text = content.countryNumeric)
            }
            Row {
                Text(text = content.countryCurrency)
                Text(text = content.bankCity)
            }
            Row {
                Text(text = content.bankName)
                Text(text = content.bankUrl)
            }
            Row {
                Text(text = content.bankPhone)
                Text(text = content.numberLength.toString())
            }
            Row {
                Text(text = if (content.numberLuhn) "Yes" else "No")
            }
        }
    }
}