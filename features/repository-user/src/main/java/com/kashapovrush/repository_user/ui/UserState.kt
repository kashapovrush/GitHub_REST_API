package com.kashapovrush.repository_user.ui

import com.kashapovrush.api.modelDto.User

sealed class UserState {

//    object Load: UserState()
    object Initial: UserState()

    data class Error(val message: String): UserState()
    data class Success (val user: User): UserState()
}