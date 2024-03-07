package com.kashapovrush.githubrestapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.navigation.AppNavGraph
import com.example.navigation.rememberNavigationState
import com.kashapovrush.githubrestapi.ui.theme.GitHubRESTAPITheme
import com.kashapovrush.repository_user.UserInfoScreen
import com.kashapovrush.repository_user.UserInfoViewModel
import com.kashapovrush.search_repository_sreen.ui.SearchRepositoriesViewModel
import com.kashapovrush.search_repository_sreen.ui.SearchRepositoryScreen
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as GitHubApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        setContent {
            val searchRepositoriesViewModel: SearchRepositoriesViewModel = viewModel(factory = viewModelFactory)
            val userInfoViewModel: UserInfoViewModel = viewModel(factory = viewModelFactory)
            GitHubRESTAPITheme {

                val navigationState = rememberNavigationState()

                AppNavGraph(
                    navHostController = navigationState.navHostController,
                    searchRepositoriesContent = {
                        SearchRepositoryScreen(
                            viewModel = searchRepositoriesViewModel,
                            navigationState = navigationState
                        )
                    },
                    userInfoContent = { login ->
                        UserInfoScreen(userInfoViewModel, login)
                    })
            }
        }
    }
}