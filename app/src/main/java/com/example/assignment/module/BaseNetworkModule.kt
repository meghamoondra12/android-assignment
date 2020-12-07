package com.example.assignment.module

import com.example.assignment.network.NetworkManager
import com.example.assignment.qualifiers.BaseUrl
import com.example.assignment.qualifiers.GenericBaseUrl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module class BaseNetworkModule {

    @Provides
    fun provideNetworkManager(): NetworkManager {
        return NetworkManager()
    }

    @GenericBaseUrl
    @Provides
    fun provideGenericRetrofit(networkManager: NetworkManager): Retrofit {
        return networkManager.getGenericNetworkClient()
    }

    @BaseUrl
    @Provides
    fun provideBaseRetrofit(networkManager: NetworkManager): Retrofit {
        return networkManager.getBaseNetworkClient()
    }
}