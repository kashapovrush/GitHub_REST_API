package com.kashapovrush.api.mapper

import com.kashapovrush.api.modelDto.SearchItemDto
import com.kashapovrush.api.modelDto.UserDto
import com.kashapovrush.network_api.entity.SearchItem
import com.kashapovrush.network_api.entity.User

fun SearchItemDto.toEntity(): SearchItem = SearchItem(
    id = id,
    name = name,
    full_name = full_name,
    image = owner?.avatar_url,
    description = description,
    login = owner?.login
)


fun List<SearchItemDto>.toEntities() = map { it.toEntity() }

fun UserDto.toEntity(): User = User(
    login = login, id = id, avatar_url = avatar_url, bio = bio)