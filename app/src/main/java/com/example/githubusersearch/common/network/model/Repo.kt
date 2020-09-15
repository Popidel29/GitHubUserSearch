package com.example.githubusersearch.common.network.model

import com.google.gson.annotations.SerializedName

data class Repo(
    val id:Int,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("html_url") val htmlUrl:String
)