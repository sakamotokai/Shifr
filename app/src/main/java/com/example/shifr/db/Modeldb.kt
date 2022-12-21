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
    var id:Int = 0,
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

class MainResponseConvertor{
    var convert = ""
    @TypeConverter
    fun convertTo(mainResponse:MainResponse):String{
        convert += mainResponse.bank.city
        convert += ","
        convert += mainResponse.bank.name
        convert += ","
        convert += mainResponse.bank.phone
        convert += ","
        convert += mainResponse.bank.url
        convert += ","
        convert += mainResponse.brand
        convert += ","
        convert += mainResponse.country.alpha2
        convert += ","
        convert += mainResponse.country.currency
        convert += ","
        convert += mainResponse.country.emoji
        convert += ","
        convert += mainResponse.country.latitude
        convert += ","
        convert += mainResponse.country.longitude
        convert += ","
        convert += mainResponse.country.name
        convert += ","
        convert += mainResponse.country.numeric
        convert += ","
        convert += mainResponse.number.length
        convert += ","
        convert += mainResponse.number.luhn
        convert += ","
        convert += mainResponse.prepaid
        convert += ","
        convert += mainResponse.scheme
        convert += ","
        convert += mainResponse.type
        return convert
    }

    /*@TypeConverter
    fun convertFrom():MainResponse{

    }*/
}