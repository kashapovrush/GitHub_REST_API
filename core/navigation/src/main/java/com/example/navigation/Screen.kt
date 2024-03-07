package com.example.navigation

sealed class Screen(val route: String) {


    object SearchRepositories: Screen(ROUTE_SEARCH_REPOSITORIES)

    object UserInfo: Screen(ROUTE_USER_INFO) {
        const val EXTRA_USER_INFO = "route_user_info"

        fun getRoute(login: String): String {
            return "$EXTRA_USER_INFO/$login"
        }
    }

    companion object {

        const val EXTRA_LOGIN = "user_id"

        const val ROUTE_SEARCH_REPOSITORIES = "route_search_repositories"
        const val ROUTE_USER_INFO = "route_user_info/{$EXTRA_LOGIN}"
    }
}
