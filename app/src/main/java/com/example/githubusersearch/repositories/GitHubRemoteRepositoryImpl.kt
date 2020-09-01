package com.example.githubusersearch.repositories

import com.example.githubusersearch.common.network.GitHubClient
import com.example.githubusersearch.common.network.model.Repo
import com.example.githubusersearch.common.network.model.User
import com.example.githubusersearch.common.network.model.UserSearchModel
import javax.inject.Inject

class GitHubRemoteRepositoryImpl @Inject constructor(private val gitHubClient: GitHubClient): GitHubRemoteRepository {

    override suspend fun getUserList(username: String): UserSearchModel {
        return gitHubClient.getUserList(username)
    }

    override suspend fun getUser(username: String): User {
        return gitHubClient.getUser(username)
    }

    override suspend fun getRepositoriesByUser(username: String): List<Repo> {
        return gitHubClient.getReposByUser(username)
    }

}