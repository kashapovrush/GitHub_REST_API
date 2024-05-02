package com.kashapovrush.network_api.usecases

import com.kashapovrush.network_api.entity.User
import com.kashapovrush.network_api.repository.SearchRepositories
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: SearchRepositories
) {

    suspend operator fun invoke(login: String): Flow<User> {
        return repository.getUser(login)
    }
}