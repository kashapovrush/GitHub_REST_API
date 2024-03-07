package com.kashapovrush.githubrestapi

import android.app.Application
import com.kashapovrush.githubrestapi.di.DaggerAppComponent
import com.kashapovrush.search_repository_sreen.di.ComponentProvider
import com.kashapovrush.search_repository_sreen.di.DaggerSearchRepositoriesComponent
import com.kashapovrush.search_repository_sreen.di.SearchRepositoriesComponent

class GitHubApp: Application(), ComponentProvider {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }


    override fun getComponentProvider(): SearchRepositoriesComponent {
        return DaggerSearchRepositoriesComponent.factory().create(this)
    }
}