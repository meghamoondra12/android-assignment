package com.example.assignment.model

import com.google.gson.annotations.SerializedName

data class BreadResponse(
    @field:SerializedName("description")
    val description: String? = null, @field:SerializedName("name")
    val name: String? = null, @field:SerializedName("id")
    val id: String? = null
)
