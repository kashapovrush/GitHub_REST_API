package com.kashapovrush.search_repository_sreen.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component
interface SearchRepositoriesComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): SearchRepositoriesComponent
    }

}