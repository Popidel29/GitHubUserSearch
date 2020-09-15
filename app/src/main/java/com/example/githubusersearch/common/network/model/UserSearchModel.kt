package com.example.githubusersearch.common.network.model

import com.google.gson.annotations.SerializedName

data class UserSearchModel(
    val items: List<Item>
) {
    data class Item(
        @SerializedName("id") val id: Int,
        @SerializedName("login") val login: String,
        @SerializedName("avatar_url") val avatarUrl: String,
        @SerializedName("public_repos") val public_repos: Int
    )
}