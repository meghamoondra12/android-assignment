package com.example.assignment.module

import com.example.assignment.network.IBaseApi
import com.example.assignment.qualifiers.BaseUrl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class Main2Module {
    @Provides
    fun provideBaseUrl(
        @BaseUrl
        retrofit: Retrofit
    ): IBaseApi {
        return retrofit.create(IBaseApi::class.java)
    }
}