package com.example.assignment.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "new_entity") data class ResponseEntity(
    @PrimaryKey @NonNull @ColumnInfo(name = "id")
    val entityId: Int = 1111111111,
    @ColumnInfo(name = "resultItem") val list: List<ResultsItem>
)