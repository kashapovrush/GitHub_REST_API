package com.kashapovrush.githubrestapi

import android.app.Application
import com.kashapovrush.githubrestapi.di.DaggerAppComponent

class GitHubApp: Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }


//    override fun getComponentProvider(): SearchRepositoriesComponent {
//        return DaggerSearchRepositoriesComponent.factory().create(this)
//    }
}