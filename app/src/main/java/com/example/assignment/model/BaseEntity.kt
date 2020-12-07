package com.example.assignment.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "base_entity") data class BaseEntity(
    @PrimaryKey @NonNull @ColumnInfo(name = "id")
    val entityId: Int = 222222222,
    @ColumnInfo(name = "breedItem") val list: List<BreadResponse>
)