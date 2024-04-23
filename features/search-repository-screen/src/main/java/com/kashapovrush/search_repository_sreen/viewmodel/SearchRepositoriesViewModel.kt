package com.kashapovrush.search_repository_sreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kashapovrush.api.network.ApiService
import com.kashapovrush.search_repository_sreen.ui.ScreenState
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
            viewModelScope.launch {
                _screenState.value = ScreenState.Error(it.message)
            }

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