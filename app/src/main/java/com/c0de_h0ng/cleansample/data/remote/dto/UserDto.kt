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
    val result = mutableListOf<User>()
    for (i in userList.indices) {
        with(userList[i]) {
            result.add(
                User(
                    id = id,
                    login = login,
                    htmlUrl = htmlUrl,
                    reposUrl = reposUrl
                )
            )
        }
    }
    return result
}