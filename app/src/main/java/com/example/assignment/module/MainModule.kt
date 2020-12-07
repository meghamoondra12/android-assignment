package com.example.assignment.module

import com.example.assignment.dao.IDao
import com.example.assignment.database.AssignmentDatabase
import com.example.assignment.network.IGenericApi
import com.example.assignment.qualifiers.GenericBaseUrl
import com.example.assignment.qualifiers.ViewModelInjection
import com.example.assignment.view.MainActivity
import com.example.assignment.viewmodel.InjectionViewModelProvider
import com.example.assignment.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {
    @Provides
    fun provideGenericApi(
        @GenericBaseUrl
        retrofit: Retrofit
    ): IGenericApi {
        return retrofit.create(IGenericApi::class.java)
    }

    @Provides
    @ViewModelInjection
    fun provideProductWishListViewModel(
        activity: MainActivity,
        viewModelProvider: InjectionViewModelProvider<MainViewModel>
    ): MainViewModel = viewModelProvider.get(activity)

    @Provides
    fun providesIDao(database: AssignmentDatabase): IDao {
        return database.getDao()
    }
}