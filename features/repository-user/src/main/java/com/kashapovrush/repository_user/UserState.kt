package com.kashapovrush.repository_user

import com.kashapovrush.api.modelDto.User

sealed class UserState {

    object Load: UserState()

    data class Success (val user: User): UserState()
}