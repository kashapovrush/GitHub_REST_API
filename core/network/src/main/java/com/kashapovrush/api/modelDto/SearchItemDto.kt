package com.kashapovrush.api.modelDto

data class SearchItemDto(
    val id: Int,
    val name: String? = null,
    val full_name: String? = null,
    val owner: OwnerDto? = null,
    val description: String? = null,
    val updated_at: String? = null
)
