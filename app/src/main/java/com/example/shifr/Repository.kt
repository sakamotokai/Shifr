package com.example.shifr

import androidx.lifecycle.LiveData
import com.example.shifr.db.Modeldb
import com.example.shifr.retrofit.Model.MainResponse
import com.example.shifr.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class Repository:InterfaceForRetrofit {
    suspend fun getPost(endPoint:String):Response<MainResponse>{
        return RetrofitInstance.api.getPost(endPoint)
    }
    val getAllModeldb:Flow<List<Modeldb>>
    get()= globalDaodb.getAllModeldb()

    suspend fun insert(modeldb: Modeldb){
        return globalDaodb.insert(modeldb)
    }
    suspend fun delete(modeldb: Modeldb){
        return globalDaodb.delete(modeldb)
    }
    suspend fun update(modeldb: Modeldb){
        return globalDaodb.update(modeldb)
    }

    val getAllModeldbLiveData: LiveData<List<Modeldb>>
    get()= globalDaodb.getAllModeldbLiveData()

    override fun getData(endPoint: String) = flow{
        emit(RetrofitInstance.api.getPost(endPoint))
    }
}