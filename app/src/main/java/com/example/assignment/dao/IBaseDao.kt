package com.example.assignment.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignment.model.BaseEntity

@Dao
interface IBaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllData(mBaseEntity: BaseEntity)

    @Query("SELECT * from base_entity WHERE id = :id")
    fun getAllData(
        id: Int
    ): LiveData<BaseEntity>
}