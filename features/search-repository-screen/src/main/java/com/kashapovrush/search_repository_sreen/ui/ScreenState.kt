package com.kashapovrush.search_repository_sreen.ui

import com.kashapovrush.network_api.entity.SearchItem

sealed class ScreenState {

    data object Loading : ScreenState()

    data object Initial : ScreenState()

    data class Error(val message: String?) : ScreenState()

    data class ShowResult(
        val repositories: List<SearchItem>
    ) : ScreenState()
}