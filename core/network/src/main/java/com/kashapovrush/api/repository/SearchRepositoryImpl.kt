package com.kashapovrush.api.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.kashapovrush.api.mapper.toEntities
import com.kashapovrush.api.mapper.toEntity
import com.kashapovrush.api.network.ApiService
import com.kashapovrush.network_api.entity.SearchItem
import com.kashapovrush.network_api.entity.User
import com.kashapovrush.network_api.repository.SearchRepositories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val pageSource: RepositoriesPageSource.Factory,
    private val apiService: ApiService
): SearchRepositories {


    override fun searchRepositories(query: String) = Pager(
        config = PagingConfig(5)
    ) {
        pageSource.create(query)
    }.flow

    override suspend fun getUser(login: String) = flow {
        emit(apiService.getUser(login).toEntity())
    }

}