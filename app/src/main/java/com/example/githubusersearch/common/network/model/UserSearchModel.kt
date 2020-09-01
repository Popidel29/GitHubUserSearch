package com.example.githubusersearch.common.network.model

import com.google.gson.annotations.SerializedName

data class UserSearchModel (
    val items: List<Item>
) {
    data class Item(
        val id: Int,
        @SerializedName("avatar_url")
        val avatarUrl: String,
        val login: String,
        val public_repos : Int
    )
}