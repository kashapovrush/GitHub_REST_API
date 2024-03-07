package com.kashapovrush.api.network

import com.kashapovrush.api.modelDto.ApiResponse
import com.kashapovrush.api.modelDto.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") searchText: String
    ): ApiResponse

    @GET("users/{login}")
    suspend fun getUser(@Path("login") login: String): User

}