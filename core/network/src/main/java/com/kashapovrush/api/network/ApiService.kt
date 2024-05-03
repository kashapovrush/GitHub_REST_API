package com.kashapovrush.api.network

import androidx.annotation.IntRange
import com.kashapovrush.api.modelDto.ApiResponse
import com.kashapovrush.api.modelDto.UserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") searchText: String,
        @Query("per_page") @IntRange(from = 1, to = 10) perPage: Int = 10,
        @Query("page") @IntRange(from = 1) page: Int = 1
    ): Response<ApiResponse>

    @GET("users/{login}")
    suspend fun getUser(@Path("login") login: String): UserDto

}