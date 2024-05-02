//package com.kashapovrush.api.repository
//
//import androidx.paging.PagingState
//import com.kashapovrush.api.network.ApiService
//import com.kashapovrush.network_api.entity.SearchItem
//import javax.inject.Inject
//
//class RepositoriesPageSource @Inject constructor(
//    private val apiService: ApiService,
//    private val query: String
//) {
//    override fun getRefreshKey(state: PagingState<Int, SearchItem>): Int? {
////        return state.anchorPosition
//        return null
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchItem> {
////        return try {
////            val start = params.key ?: 1
////            val limit = 20
////            val response =
////                apiService.searchRepositories("kotlin", start, limit) ?: emptyList<SearchItem>()
////            val nextKey = if (response.isEmpty()) null else response.size.plus(start).plus(1)
////            val prevKey = if (start == 1) null else response.size.minus(limit)
////            LoadResult.Page(
////                response, nextKey, prevKey
////            )
////        } catch (e: Exception) {
////            return LoadResult.Error(e)
////        }
//        return null
//    }
//}