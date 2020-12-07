package com.example.assignment.module

import com.example.assignment.view.AssignmentActivity
import com.example.assignment.view.MainActivity
import com.example.assignment.view.MainActivity2
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjectorsModule {

    @ContributesAndroidInjector()
    abstract fun mainAssignmentActivity(): AssignmentActivity

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainActivityInjector(): MainActivity

    @ContributesAndroidInjector(modules = [Main2Module::class, FragmentInjectorModule::class])
    abstract fun mainActivity2Injector(): MainActivity2
}