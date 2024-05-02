package com.kashapovrush.repository_user.viewmodel

import androidx.lifecycle.ViewModel
import com.kashapovrush.network_api.usecases.GetUserUseCase
import com.kashapovrush.repository_user.ui.UserState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserInfoViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO)

    private val _user = MutableStateFlow<UserState>(UserState.Initial)
    val user: MutableStateFlow<UserState> = _user


    suspend fun getUser(login: String) {
        scope.launch {
            getUserUseCase(login)
                .catch {
                    _user.value = UserState.Error(it.message ?: "Unknown error")
                }.collect {
                    _user.value = UserState.Success(it)
                }
        }

    }

    override fun onCleared() {
        scope.cancel()
        super.onCleared()
    }
}