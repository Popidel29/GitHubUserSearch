package com.example.githubusersearch.di.component

import com.example.githubusersearch.MyApp
import com.example.githubusersearch.common.network.GitHubClient
import com.example.githubusersearch.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface AppComponent {
    fun inject(inject: MyApp)
    fun client(): GitHubClient
}