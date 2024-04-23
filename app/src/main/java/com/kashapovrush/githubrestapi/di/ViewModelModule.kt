package com.kashapovrush.githubrestapi.di

import androidx.lifecycle.ViewModel
import com.kashapovrush.repository_user.viewmodel.UserInfoViewModel
import com.kashapovrush.search_repository_sreen.viewmodel.SearchRepositoriesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(SearchRepositoriesViewModel::class)
    @Binds
    fun bindSearchRepositoriesViewModel(viewModel: SearchRepositoriesViewModel): ViewModel

    @IntoMap
    @ViewModelKey(UserInfoViewModel::class)
    @Binds
    fun bindUserInfoViewModel(viewModel: UserInfoViewModel): ViewModel
}