package com.example.assignment.module

import com.example.assignment.dao.IBaseDao
import com.example.assignment.database.AssignmentDatabase
import com.example.assignment.qualifiers.ViewModelInjection
import com.example.assignment.view.ViewPagerFragment
import com.example.assignment.viewmodel.InjectionViewModelProvider
import com.example.assignment.viewmodel.MainViewmodel2
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    @ViewModelInjection
    fun provideProductWishListViewModel(
        fragment: ViewPagerFragment,
        viewModelProvider: InjectionViewModelProvider<MainViewmodel2>
    ): MainViewmodel2 = viewModelProvider.get(fragment)

    @Provides
    fun providesIDao(database: AssignmentDatabase): IBaseDao {
        return database.getBaseDao()
    }
}