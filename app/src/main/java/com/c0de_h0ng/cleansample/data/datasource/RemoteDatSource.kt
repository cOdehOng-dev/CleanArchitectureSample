package com.c0de_h0ng.cleansample.data.datasource

import com.c0de_h0ng.cleansample.data.remote.dto.UserDto
import io.reactivex.Observable

/**
 * Created by c0de_h0ng on 2022/02/01.
 */
interface RemoteDatSource {

    suspend fun getUserList(searchWord: String): UserDto

    fun getRxUserList(searchWord: String): Observable<UserDto>
}