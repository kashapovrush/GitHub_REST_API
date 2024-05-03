package com.kashapovrush.network_api.usecases

import androidx.paging.PagingData
import com.kashapovrush.network_api.entity.SearchItem
import com.kashapovrush.network_api.repository.SearchRepositories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class SearchRepositoriesUseCase @Inject constructor(
    private val repository: SearchRepositories
) {

    operator fun invoke(query: String): Flow<PagingData<SearchItem>> {
        return repository.searchRepositories(query)
    }
}