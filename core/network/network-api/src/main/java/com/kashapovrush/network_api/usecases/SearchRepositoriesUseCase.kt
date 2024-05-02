package com.kashapovrush.network_api.usecases

import com.kashapovrush.network_api.entity.SearchItem
import com.kashapovrush.network_api.repository.SearchRepositories
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoriesUseCase @Inject constructor(
    private val repository: SearchRepositories
) {

    suspend operator fun invoke(query: String): Flow<List<SearchItem>> {
        return repository.searchRepositories(query)
    }
}