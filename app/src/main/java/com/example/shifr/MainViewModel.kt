package com.example.shifr

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shifr.retrofit.Model.MainResponse
import androidx.lifecycle.ViewModel
import com.example.shifr.db.Database
import com.example.shifr.db.Modeldb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel() : ViewModel() {

    var myResponse: MutableLiveData<Response<MainResponse>> = MutableLiveData()
    private var _flowResponse:MutableStateFlow<Response<MainResponse>?> = MutableStateFlow(null)
    var flowResponse:StateFlow<Response<MainResponse>?> = _flowResponse

    fun initDatabase(context: Context){
        globalDaodb = Database.getInstance(context).getDao()
    }

    fun getRequast(endPoint:String) {
        viewModelScope.launch {
            val response = Repository().getPost(endPoint)
            //myResponse.value = response
            _flowResponse.value = response
        }
    }

    /*fun getRequast(endPoint:String) {
        viewModelScope.launch {
            val response = Repository().getPost(endPoint)
            myResponse.value = response
        }
    }*/

    fun getAllModel():Flow<List<Modeldb>>{
        return globalDaodb.getAllModeldb()
    }


    /*val getAllModeldb: Flow<List<Modeldb>>
        get() = globalDaodb.getAllModeldb()*/

    fun insert(modeldb: Modeldb) {
        viewModelScope.launch {
            globalDaodb.insert(modeldb)
        }
    }

    fun delete(modeldb: Modeldb) {
        viewModelScope.launch{
            globalDaodb.delete(modeldb)
        }
    }

    fun update(modeldb: Modeldb) {
        viewModelScope.launch {
            globalDaodb.update(modeldb)
        }
    }
}