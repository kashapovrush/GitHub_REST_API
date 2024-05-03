package com.kashapovrush.api.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kashapovrush.api.mapper.toEntity
import com.kashapovrush.api.network.ApiService
import com.kashapovrush.network_api.entity.SearchItem
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class RepositoriesPageSource @AssistedInject constructor(
    private val apiService: ApiService,
    @Assisted("query") private val query: String
) : PagingSource<Int, SearchItem>() {
    override fun getRefreshKey(state: PagingState<Int, SearchItem>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page =
            state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchItem> {
        if (query.isEmpty()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }

        val page = params.key ?: 1
        val pageSize = params.loadSize.coerceAtMost(10)

        val response =
            apiService.searchRepositories(searchText = query, perPage = pageSize, page = page)

        if (response.isSuccessful) {
            val repositories = checkNotNull(response.body()?.items?.map { it.toEntity() })
            val nextKey = if (repositories.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            return LoadResult.Page(data  = repositories, prevKey = prevKey, nextKey = nextKey)
        } else {
            return LoadResult.Error(retrofit2.HttpException(response))
        }
    }

    @AssistedFactory
    interface Factory {

        fun create(@Assisted("query") query: String): RepositoriesPageSource
    }


}