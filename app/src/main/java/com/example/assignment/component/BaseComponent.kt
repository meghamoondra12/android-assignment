package com.example.assignment.component

import com.example.assignment.application.BaseApplication
import com.example.assignment.database.AssignmentDatabase
import com.example.assignment.module.ActivityInjectorsModule
import com.example.assignment.module.ApplicationModule
import com.example.assignment.module.BaseNetworkModule
import com.example.assignment.module.FragmentInjectorModule
import com.example.assignment.qualifiers.BaseUrl
import com.example.assignment.qualifiers.GenericBaseUrl
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import retrofit2.Retrofit


@Component(modules = [ApplicationModule::class, BaseNetworkModule::class, AndroidInjectionModule::class, ActivityInjectorsModule::class])
interface BaseComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BaseApplication): Builder

        fun build(): BaseComponent
    }

    fun inject(app: BaseApplication)

    @GenericBaseUrl
    fun genericRetrofit(): Retrofit

    @BaseUrl
    fun baseRetrofit(): Retrofit

    fun getDatabase(): AssignmentDatabase
}