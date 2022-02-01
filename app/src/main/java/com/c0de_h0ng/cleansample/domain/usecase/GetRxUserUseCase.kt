package com.c0de_h0ng.cleansample.domain.usecase

import android.util.Log
import com.c0de_h0ng.cleansample.common.CallResult
import com.c0de_h0ng.cleansample.common.RxUseCase
import com.c0de_h0ng.cleansample.data.remote.dto.UserDto
import com.c0de_h0ng.cleansample.domain.repository.GitHubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2022/02/01.
 */
class GetRxUserUseCase @Inject constructor(
    private val repository: GitHubRepository
) : RxUseCase<UserDto>() {

    operator fun invoke(searchWord: String): Disposable {
        return repository.getRxUserList(searchWord)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                result.value = CallResult.Loading(isLoading = true)
            }
            .doOnTerminate {
                result.value = CallResult.Loading(isLoading = false)
            }
            .subscribe(
                {
                    Log.d("Result >>> ", "Success")
                    result.value = CallResult.Success(it)
                }, {
                    Log.d("Result >>> ", it.localizedMessage ?: "An unexpected error occured")
                    result.value = CallResult.Error(it.localizedMessage ?: "An unexpected error occured")
                }
            )
    }
}