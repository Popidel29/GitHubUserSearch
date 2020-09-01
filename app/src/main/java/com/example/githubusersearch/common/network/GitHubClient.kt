package com.example.githubusersearch.common.network

import com.example.githubusersearch.common.*
import com.example.githubusersearch.common.network.model.Repo
import com.example.githubusersearch.common.network.model.User
import com.example.githubusersearch.common.network.model.UserSearchModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubClient {
    @GET(USERS_SEARCH_ENDPOINT)
    suspend fun getUserList(@Query("q") username: String): UserSearchModel

    @GET(GET_USER_ENDPOINT)
    suspend fun getUser(@Path(USERNAME) username: String): User

    @GET(GET_USER_REPOS_ENDPOINT)
    suspend fun getReposByUser(@Path(USERNAME) username: String): List<Repo>
}