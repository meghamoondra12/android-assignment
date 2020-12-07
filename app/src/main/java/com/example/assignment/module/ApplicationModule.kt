package com.example.assignment.module

import androidx.room.Room
import com.example.assignment.application.BaseApplication
import com.example.assignment.database.AssignmentDatabase
import dagger.Module
import dagger.Provides

@Module class ApplicationModule {

    @Provides
    fun providesAssignmentDatabase(): AssignmentDatabase {
        return Room.databaseBuilder(
            BaseApplication.instance.applicationContext,
            AssignmentDatabase::class.java, "LoginPrefs"
        ).fallbackToDestructiveMigration().build()
    }
}