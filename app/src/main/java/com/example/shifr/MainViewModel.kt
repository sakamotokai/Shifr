package com.example.shifr

import android.content.Context
import android.util.Log
import androidx.compose.material.ModalBottomSheetDefaults
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shifr.retrofit.Model.MainResponse
import androidx.lifecycle.ViewModel
import com.example.shifr.db.Database
import com.example.shifr.db.Modeldb
import com.example.shifr.retrofit.Model.Bank
import com.example.shifr.retrofit.Model.Country
import com.example.shifr.retrofit.Model.Number
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel() : ViewModel() {

    private val repository: Repository = Repository()
    private var myResponse: MutableLiveData<Response<MainResponse>> = MutableLiveData()
    var MyResponse: LiveData<Response<MainResponse>> = myResponse
    private var _response: MutableStateFlow<Response<MainResponse>?> = MutableStateFlow(null)
    var response: StateFlow<Response<MainResponse>?> = _response
    private var _flowResponse: MutableStateFlow<Response<MainResponse>?> = MutableStateFlow(null)

    var flowResponse: StateFlow<Response<MainResponse>?> = _flowResponse

    private var _flowSavedb: MutableStateFlow<List<Modeldb>?> = MutableStateFlow(null)
    var flowSavedb: StateFlow<List<Modeldb>?> = _flowSavedb
    var listData:MainResponse? = null

    fun initDatabase(context: Context) {
        globalDaodb = Database.getInstance(context).getDao()
    }

    fun getRequast(endPoint: String) {
        viewModelScope.launch {
            val response = repository.getPost(endPoint)
            _flowResponse.value = response
        }
    }

    fun getRequastFlow(endPoint: String){
        viewModelScope.launch {
            repository.getData(endPoint)
                .flowOn(Dispatchers.IO)
                .collect{
                    listData = it.body()
                }
        }
    }

    fun getRequastLiveData(endPoint: String) {
        viewModelScope.launch {
            val response = repository.getPost(endPoint)
            if (response.isSuccessful) {
                myResponse.value = response
            } else {
                Log.e("Log", response.message())
            }
        }
    }

    fun updateDataFromdb() {
        viewModelScope.launch(Dispatchers.IO) {
            Repository().getAllModeldb.collect {
                _flowSavedb.value = it
            }
        }
    }

    fun getAllModel(): Flow<List<Modeldb>> {
        return globalDaodb.getAllModeldb()
    }

    fun getAllModelLiveData(): LiveData<List<Modeldb>> {
        return Repository().getAllModeldbLiveData
    }


    /*val getAllModeldb: Flow<List<Modeldb>>
        get() = globalDaodb.getAllModeldb()*/

    fun insert(modeldb: Modeldb) {
        viewModelScope.launch {
            globalDaodb.insert(modeldb)
        }
    }

    fun delete(modeldb: Modeldb) {
        viewModelScope.launch {
            globalDaodb.delete(modeldb)
        }
    }

    fun update(modeldb: Modeldb) {
        viewModelScope.launch {
            globalDaodb.update(modeldb)
        }
    }
}