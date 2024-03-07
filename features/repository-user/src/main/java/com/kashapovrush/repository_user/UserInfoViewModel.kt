package com.kashapovrush.repository_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashapovrush.api.network.ApiService
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserInfoViewModel @Inject constructor(
    private val apiService: ApiService
): ViewModel() {

    private val _user = MutableLiveData<UserState>(UserState.Load)
    val user: LiveData<UserState> = _user

    suspend fun getUser(login: String) = flow {
        emit(apiService.getUser(login))
    }
        .collect {
            viewModelScope.launch {
                _user.value = UserState.Success(it)
            }

        }
}