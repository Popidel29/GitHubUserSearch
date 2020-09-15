package com.example.githubusersearch.di.component

import com.example.githubusersearch.di.module.UserDetailViewModelModule
import com.example.githubusersearch.di.scope.UserActivityScope
import com.example.githubusersearch.view.UserDetailActivity
import dagger.Component

@Component(modules = [UserDetailViewModelModule::class],dependencies = [AppComponent::class])
@UserActivityScope
interface UserDetailComponent {
    fun inject(userDetailActivity: UserDetailActivity)
}