package com.kashapovrush.search_repository_sreen.ui

import com.kashapovrush.api.modelDto.SearchItem

sealed class ScreenState {

    object Loading : ScreenState()

    object Initial : ScreenState()

    data class Error(val message: String?) : ScreenState()

    data class ShowResult(
        val repositories: List<SearchItem>
    ) : ScreenState()
}