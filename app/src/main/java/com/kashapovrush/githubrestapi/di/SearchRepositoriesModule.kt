package com.kashapovrush.githubrestapi.di

import com.kashapovrush.api.network.ApiFactory
import com.kashapovrush.api.network.ApiService
import com.kashapovrush.util.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
interface SearchRepositoriesModule {

    companion object {
        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}