package com.example.githubusersearch.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.githubusersearch.view.UserSearchActivity
import com.example.githubusersearch.common.network.GitHubClient
import com.example.githubusersearch.di.scope.UserActivityScope
import com.example.githubusersearch.repositories.GitHubRemoteRepository
import com.example.githubusersearch.repositories.GitHubRemoteRepositoryImpl
import com.example.githubusersearch.viewmodel.GitHubViewModel
import com.example.githubusersearch.viewmodel.GitHubViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class GitHubViewModelModule( private val activity: UserSearchActivity
) {

    @Provides
    @UserActivityScope
    fun provideGitHubViewModel(viewModelFactory: GitHubViewModelFactory): GitHubViewModel {
        return ViewModelProvider(activity, viewModelFactory).get(GitHubViewModel::class.java)
    }

    @Provides
    @UserActivityScope
    fun provideGitHubViewModelFactory(repository: GitHubRemoteRepository): GitHubViewModelFactory {
    return GitHubViewModelFactory(repository)
    }

    @Provides
    @UserActivityScope
    fun provideRepository(gitHubClient: GitHubClient): GitHubRemoteRepository {
        return GitHubRemoteRepositoryImpl(gitHubClient)
    }

}