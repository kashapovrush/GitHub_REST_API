package com.kashapovrush.network_api.repository

import androidx.paging.PagingData
import com.kashapovrush.network_api.entity.SearchItem
import com.kashapovrush.network_api.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface SearchRepositories {

    fun searchRepositories(query: String): Flow<PagingData<SearchItem>>

    suspend fun getUser(login: String): Flow<User>
}