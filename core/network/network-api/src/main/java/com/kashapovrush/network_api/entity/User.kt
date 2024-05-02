package com.kashapovrush.network_api.entity

data class User(
    val login: String? = null,
    val id: Int,
    val avatar_url: String? = null,
    val bio: String? = null,
    val public_repos: Int? = 0,
    val followers: Int? = 0
)
