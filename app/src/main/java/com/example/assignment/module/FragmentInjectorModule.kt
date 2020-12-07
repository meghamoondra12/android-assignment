package com.example.assignment.module

import com.example.assignment.view.MainActivity2
import com.example.assignment.view.ViewPagerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentInjectorModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun mainViewPagerFragment(): ViewPagerFragment
}