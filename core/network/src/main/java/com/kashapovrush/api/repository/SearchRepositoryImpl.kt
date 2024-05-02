package com.kashapovrush.api.repository

import com.kashapovrush.api.mapper.toEntities
import com.kashapovrush.api.mapper.toEntity
import com.kashapovrush.api.network.ApiService
import com.kashapovrush.network_api.entity.User
import com.kashapovrush.network_api.repository.SearchRepositories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): SearchRepositories {
    override suspend fun searchRepositories(query: String) = flow {
        emit(apiService.searchRepositories(query).items)
    }.map {searchItemDtos ->
        searchItemDtos.toEntities()
    }

    override suspend fun getUser(login: String): Flow<User>  = flow {
         emit(apiService.getUser(login))
    }.map {
        it.toEntity()
    }

}