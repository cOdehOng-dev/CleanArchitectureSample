package com.c0de_h0ng.cleansample.data.remote

import com.c0de_h0ng.cleansample.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by c0de_h0ng on 2022/01/21.
 */
interface GitHubApi {

    @GET("users")
    suspend fun getUser(
        @Query("q") searchUser: String
    ) : UserDto

}