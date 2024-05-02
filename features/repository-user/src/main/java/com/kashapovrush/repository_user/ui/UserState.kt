package com.kashapovrush.repository_user.ui

import com.kashapovrush.network_api.entity.User

sealed class UserState {

//    object Load: UserState()
data object Initial: UserState()

    data class Error(val message: String): UserState()
    data class Success (val user: User): UserState()
}