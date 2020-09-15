package com.example.githubusersearch

import android.app.Application
import com.example.githubusersearch.di.component.AppComponent
import com.example.githubusersearch.di.component.DaggerAppComponent

import com.example.githubusersearch.di.module.NetworkModule

class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()
        component().inject(this)

    }
    fun component(): AppComponent {
        return  DaggerAppComponent.builder().networkModule(NetworkModule()).build()
    }
}