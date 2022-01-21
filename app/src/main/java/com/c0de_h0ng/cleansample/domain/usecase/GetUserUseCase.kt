package com.c0de_h0ng.cleansample.domain.usecase

import com.c0de_h0ng.cleansample.common.Resource
import com.c0de_h0ng.cleansample.data.remote.dto.toUserList
import com.c0de_h0ng.cleansample.domain.model.User
import com.c0de_h0ng.cleansample.domain.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2022/01/21.
 */
class GetUserUseCase @Inject constructor(
    private val repository: GitHubRepository
) {

    operator fun invoke(searchUser: String): Flow<Resource<List<User>>> = flow {
        try {
            emit(Resource.Loading<List<User>>())
            val user = repository.getUserList(searchUser).toUserList()
            emit(Resource.Success<List<User>>(user))
        } catch (e: HttpException) {
            emit(Resource.Error<List<User>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<User>>("Couldn't reach server. Check your internet"))
        }
    }
}