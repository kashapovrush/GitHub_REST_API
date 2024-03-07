package com.kashapovrush.githubrestapi.di

import android.content.Context
import com.kashapovrush.githubrestapi.MainActivity
import com.kashapovrush.util.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [ViewModelModule::class, SearchRepositoriesModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}