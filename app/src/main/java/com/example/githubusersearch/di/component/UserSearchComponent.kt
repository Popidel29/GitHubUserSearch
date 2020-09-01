package com.example.githubusersearch.di.component

import com.example.githubusersearch.di.module.GitHubViewModelModule
import com.example.githubusersearch.di.scope.UserActivityScope
import com.example.githubusersearch.view.UserSearchActivity
import dagger.Component

@Component(modules = [GitHubViewModelModule::class], dependencies = [AppComponent::class])
@UserActivityScope
interface UserSearchComponent {
    fun inject(searchActivity: UserSearchActivity)
}