package com.example.shifr

import com.example.shifr.db.Modeldb
import com.example.shifr.retrofit.Model.MainResponse
import com.example.shifr.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class Repository {
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
}