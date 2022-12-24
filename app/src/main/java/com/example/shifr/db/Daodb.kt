package com.example.shifr.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface Daodb {
    @Insert
    suspend fun insert(modeldb: Modeldb)
    @Delete
    suspend fun delete(modeldb: Modeldb)
    @Update
    suspend fun update(modeldb: Modeldb)
    @Query("SELECT*FROM requestHistory")
    fun getAllModeldb(): Flow<List<Modeldb>>
    @Query("SELECT*FROM requestHistory")
    fun getAllModeldbLiveData(): LiveData<List<Modeldb>>
}