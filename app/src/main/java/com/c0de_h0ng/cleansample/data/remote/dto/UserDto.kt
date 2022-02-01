package com.c0de_h0ng.cleansample.data.remote.dto

import com.c0de_h0ng.cleansample.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("incomplete_results")
    val isIncompleteResults: Boolean,
    @SerializedName("items")
    val userList: List<Item>,
    @SerializedName("total_count")
    val totalCount: Int
)

fun UserDto.toUserList(): List<User> {
    return userList.toList().map {
        User(
            id = it.id,
            login = it.login,
            htmlUrl = it.htmlUrl,
            reposUrl = it.reposUrl
        )
    }
}