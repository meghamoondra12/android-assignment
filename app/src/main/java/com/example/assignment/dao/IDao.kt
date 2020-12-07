package com.example.assignment.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignment.model.NewYorkResponse
import com.example.assignment.model.ResponseEntity

@Dao
interface IDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllData(mResponseEntity: ResponseEntity)

    @Query("DELETE FROM new_entity")
    fun deleteAllData()

    @Query("SELECT * from new_entity WHERE id = :id")
    fun getAllData(
        id: Int
    ): LiveData<ResponseEntity>
}