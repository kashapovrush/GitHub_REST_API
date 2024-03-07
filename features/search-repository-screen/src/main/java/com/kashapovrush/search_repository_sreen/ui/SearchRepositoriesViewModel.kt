package com.kashapovrush.search_repository_sreen.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.kashapovrush.api.modelDto.SearchItem
import com.kashapovrush.api.network.ApiService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchRepositoriesViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {


    private val _screenState = MutableLiveData<ScreenState>(ScreenState.Initial)
    val screenState: LiveData<ScreenState> = _screenState


    suspend fun searchRepositories(searchText: String) = flow {
        viewModelScope.launch {
            _screenState.value = ScreenState.Loading
        }
        emit(apiService.searchRepositories(searchText))
    }
        .catch {
            _screenState.postValue(ScreenState.Error(it.message))
        }
        .collect {
            viewModelScope.launch {
                _screenState.value = ScreenState.ShowResult(it.items)
            }

        }

//    fun pagingRepositories() = Pager(
//        config = PagingConfig(20),
//        pagingSourceFactory = {
//            RepositoriesPageSource(apiService)
//        }
//    ).flow.cachedIn(viewModelScope)

}