package com.example.assignment.database

import androidx.room.TypeConverter
import com.example.assignment.model.BreadResponse
import com.example.assignment.model.ResultsItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {
    @TypeConverter
    fun toList(value: String?): List<ResultsItem>? {
        val gson = Gson()
        val type = object : TypeToken<List<ResultsItem>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromList(value: List<ResultsItem>?): String? {
        val gson = Gson()
        val type = object : TypeToken<List<ResultsItem>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toBreedList(value: String?): List<BreadResponse>? {
        val gson = Gson()
        val type = object : TypeToken<List<BreadResponse>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromBreedList(value: List<BreadResponse>?): String? {
        val gson = Gson()
        val type = object : TypeToken<List<BreadResponse>>() {}.type
        return gson.toJson(value, type)
    }
}