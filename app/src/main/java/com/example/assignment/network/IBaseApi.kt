package com.example.assignment.network

import com.example.assignment.model.BreadResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface IBaseApi {


    @GET("v1/breeds")
    fun getBreads(
        @Query("attach_breed") attachBreed: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Header("x-api-key") header: String = "1c0618b1-cdfe-4280-9038-7f7f019dde8a"
    ): Single<List<BreadResponse>?>
}