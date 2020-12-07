package com.example.assignment.network

import com.example.assignment.model.NewYorkResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IGenericApi {
    @GET("svc/topstories/v2/science.json")
    fun getPopularStories(@Query("api-key") apiKey: String): Single<NewYorkResponse>
}