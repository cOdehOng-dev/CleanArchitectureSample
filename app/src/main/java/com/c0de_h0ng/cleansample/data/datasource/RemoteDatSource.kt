package com.c0de_h0ng.cleansample.data.datasource

import com.c0de_h0ng.cleansample.data.remote.dto.UserDto

/**
 * Created by c0de_h0ng on 2022/02/01.
 */
interface RemoteDatSource {

    suspend fun getUserList(searchWord: String): UserDto
}