package com.example.githubusersearch.repositories

import com.example.githubusersearch.common.network.model.Repo
import com.example.githubusersearch.common.network.model.User
import com.example.githubusersearch.common.network.model.UserSearchModel

interface GitHubRemoteRepository {

    suspend fun getUserList(username: String): UserSearchModel

    suspend fun getUser(username: String): User

    suspend fun getRepositoriesByUser(username: String): List<Repo>
}