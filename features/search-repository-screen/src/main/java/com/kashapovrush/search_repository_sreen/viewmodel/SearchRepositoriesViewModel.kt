package com.kashapovrush.search_repository_sreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kashapovrush.network_api.usecases.GetUserUseCase
import com.kashapovrush.network_api.usecases.SearchRepositoriesUseCase
import com.kashapovrush.search_repository_sreen.ui.ScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchRepositoriesViewModel @Inject constructor(
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO)

    private val _screenState = MutableLiveData<ScreenState>(ScreenState.Initial)
    val screenState: LiveData<ScreenState> = _screenState

    suspend fun searchRepositories(query: String) {
        scope.launch {
            _screenState.postValue(ScreenState.Loading)
            searchRepositoriesUseCase(query)
                .catch {
                    _screenState.postValue(ScreenState.Error(it.message))
                }.collect {
                    _screenState.postValue(ScreenState.ShowResult(it))
                }
        }
    }

    override fun onCleared() {
        scope.cancel()
        super.onCleared()
    }


//    suspend fun searchRepositories(searchText: String) = flow {
//
//        viewModelScope.launch {
//            _screenState.value = ScreenState.Loading
//        }
//
//        emit(apiService.searchRepositories(searchText))
//    }
//        .catch {
//            viewModelScope.launch {
//                _screenState.value = ScreenState.Error(it.message)
//            }
//
//        }
//        .collect {
//            viewModelScope.launch {
//                _screenState.value = ScreenState.ShowResult(it.items)
//            }
//
//        }

//    fun pagingRepositories() = Pager(
//        config = PagingConfig(20),
//        pagingSourceFactory = {
//            RepositoriesPageSource(apiService)
//        }
//    ).flow.cachedIn(viewModelScope)

}