package com.c0de_h0ng.cleansample.data.repository

import com.c0de_h0ng.cleansample.data.remote.GitHubApi
import com.c0de_h0ng.cleansample.data.remote.dto.UserDto
import com.c0de_h0ng.cleansample.domain.repository.GitHubRepository
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2022/01/21.
 */
class GitHubRepositoryImpl @Inject constructor(
    private val api: GitHubApi
) : GitHubRepository {

    override suspend fun getUserList(searchWord: String): UserDto {
        return api.getUser(searchWord)
    }

}