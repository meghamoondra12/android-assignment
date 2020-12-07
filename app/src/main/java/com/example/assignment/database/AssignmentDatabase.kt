package com.example.assignment.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.assignment.dao.IBaseDao
import com.example.assignment.dao.IDao
import com.example.assignment.model.BaseEntity
import com.example.assignment.model.ResponseEntity

@Database(
    entities = [ResponseEntity::class, BaseEntity::class],
    version = 2, exportSchema = false
) @TypeConverters(DataConverter::class) abstract class AssignmentDatabase : RoomDatabase() {
    abstract fun getDao(): IDao
    abstract fun getBaseDao(): IBaseDao
}