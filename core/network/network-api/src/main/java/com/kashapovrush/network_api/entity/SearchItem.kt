package com.kashapovrush.network_api.entity

data class SearchItem(
    val id: Int,
    val name: String? = null,
    val full_name: String? = null,
    val owner: Owner? = null,
    val description: String? = null,
    val updated_at: String? = null,
    val image: String? = null,
    val login: String? = null
)
