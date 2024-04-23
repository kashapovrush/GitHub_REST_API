package com.kashapovrush.repository_user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashapovrush.api.network.ApiService
import com.kashapovrush.repository_user.ui.UserState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserInfoViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _user = MutableStateFlow<UserState>(UserState.Initial)
    val user: MutableStateFlow<UserState> = _user

    suspend fun getUser(login: String) = flow {
//        viewModelScope.launch {
//            _user.value = UserState.Load
//        }
        emit(apiService.getUser(login))
    }.catch {
        viewModelScope.launch {
            _user.value = UserState.Error(it.message ?: "")
        }
    }.collect {
        viewModelScope.launch {
            _user.value = UserState.Success(it)
        }


    }
}