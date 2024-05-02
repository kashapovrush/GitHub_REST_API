package com.kashapovrush.api.modelDto

data class UserDto(
    val login: String? = null,
    val id: Int,
    val avatar_url: String? = null,
    val bio: String? = null,
    val public_repos: Int? = 0,
    val followers: Int? = 0
)
