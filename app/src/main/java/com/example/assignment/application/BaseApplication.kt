package com.example.assignment.application

import android.app.Application
import com.example.assignment.component.BaseComponent
import com.example.assignment.component.DaggerBaseComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class BaseApplication : Application(), HasAndroidInjector {
    lateinit var baseComponent: BaseComponent

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    companion object {
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        baseComponent = DaggerBaseComponent.builder().application(this).build()
        baseComponent.inject(this)
        super.onCreate()
        instance = this

    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

}