package com.kashapovrush.api.modelDto


data class ApiResponse(
    val total_count: Int?,
    val items: List<SearchItem>
)
