package com.kashapovrush.githubrestapi.di

import com.kashapovrush.api.network.ApiFactory
import com.kashapovrush.api.network.ApiService
import com.kashapovrush.api.repository.SearchRepositoryImpl
import com.kashapovrush.network_api.repository.SearchRepositories
import com.kashapovrush.util.ApplicationScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface SearchRepositoriesModule {

    @Binds
    fun bindsSearchRepositories(impl: SearchRepositoryImpl): SearchRepositories

    companion object {
        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}