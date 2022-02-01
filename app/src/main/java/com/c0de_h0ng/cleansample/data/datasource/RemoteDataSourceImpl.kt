package com.c0de_h0ng.cleansample.data.datasource

import com.c0de_h0ng.cleansample.data.remote.GitHubApi
import com.c0de_h0ng.cleansample.data.remote.dto.UserDto
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2022/02/01.
 */
class RemoteDataSourceImpl @Inject constructor(
    private val api: GitHubApi
) : RemoteDatSource {

    override suspend fun getUserList(searchWord: String): UserDto {
        return api.getUserList(searchWord)
    }

    override fun getRxUserList(searchWord: String): Observable<UserDto> {
        return api.getRxUserList(searchWord)
    }
}