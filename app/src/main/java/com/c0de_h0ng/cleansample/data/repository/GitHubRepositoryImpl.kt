package com.c0de_h0ng.cleansample.data.repository

import com.c0de_h0ng.cleansample.data.datasource.RemoteDatSource
import com.c0de_h0ng.cleansample.data.remote.GitHubApi
import com.c0de_h0ng.cleansample.data.remote.dto.UserDto
import com.c0de_h0ng.cleansample.domain.repository.GitHubRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2022/01/21.
 */
class GitHubRepositoryImpl @Inject constructor(
    private val remote: RemoteDatSource
) : GitHubRepository {

    override suspend fun getUserList(searchWord: String): UserDto {
        return remote.getUserList(searchWord)
    }

    override fun getRxUserList(searchWord: String): Observable<UserDto> {
        return remote.getRxUserList(searchWord).subscribeOn(Schedulers.io())
    }

}