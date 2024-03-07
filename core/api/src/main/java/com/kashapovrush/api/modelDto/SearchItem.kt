package com.kashapovrush.api.modelDto

data class SearchItem(
    val id: Int,
    val name: String? = null,
    val full_name: String? = null,
    val owner: Owner? = null,
    val description: String? = null,
    val updated_at: String? = null
)
