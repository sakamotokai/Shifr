package com.example.shifr.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.shifr.retrofit.Model.Bank
import com.example.shifr.retrofit.Model.Country
import com.example.shifr.retrofit.Model.MainResponse
import com.example.shifr.retrofit.Model.Number

@Entity(tableName = "requestHistory")
class Modeldb(
    //TODO("convert bank and so on to string value: bankName =
    // bankAddress = and so on")
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo
    val bankCity: String,
    val bankName: String,
    val bankPhone: String,
    val bankUrl: String,
    val brand: String,
    val countryAlpha2: String,
    val countryCurrency: String,
    val countryEmoji: String,
    val countryLatitude: Int,
    val countryLongitude: Int,
    val countryName: String,
    val countryNumeric: String,
    val numberLength: Int,
    val numberLuhn: Boolean,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
)
