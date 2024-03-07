package com.kashapovrush.search_repository_sreen.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kashapovrush.api.modelDto.SearchItem
import com.kashapovrush.api.network.ApiService
import javax.inject.Inject

class RepositoriesPageSource @Inject constructor(
    private val apiService: ApiService
) : PagingSource<Int, SearchItem>() {
    override fun getRefreshKey(state: PagingState<Int, SearchItem>): Int? {
//        return state.anchorPosition
        TODO()
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchItem> {
//        return try {
//            val start = params.key ?: 1
//            val limit = 20
//            val response =
//                apiService.searchRepositories("kotlin", start, limit) ?: emptyList<SearchItem>()
//            val nextKey = if (response.isEmpty()) null else response.size.plus(start).plus(1)
//            val prevKey = if (start == 1) null else response.size.minus(limit)
//            LoadResult.Page(
//                response, nextKey, prevKey
//            )
//        } catch (e: Exception) {
//            return LoadResult.Error(e)
//        }
        TODO()
    }
}