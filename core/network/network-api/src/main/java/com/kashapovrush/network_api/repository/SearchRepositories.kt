package com.kashapovrush.network_api.repository

import com.kashapovrush.network_api.entity.SearchItem
import com.kashapovrush.network_api.entity.User
import kotlinx.coroutines.flow.Flow

interface SearchRepositories {

    suspend fun searchRepositories(query: String): Flow<List<SearchItem>>

    suspend fun getUser(login: String): Flow<User>
}