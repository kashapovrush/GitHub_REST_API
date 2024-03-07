package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    searchRepositoriesContent: @Composable () -> Unit,
    userInfoContent: @Composable (String) -> Unit
) {

    NavHost(navController = navHostController, startDestination = Screen.SearchRepositories.route) {
        composable(Screen.SearchRepositories.route) {
            searchRepositoriesContent()
        }

        composable(Screen.UserInfo.route) {
            val login = it.arguments?.getString(Screen.EXTRA_LOGIN) ?: ""
            userInfoContent(login)
        }
    }

}