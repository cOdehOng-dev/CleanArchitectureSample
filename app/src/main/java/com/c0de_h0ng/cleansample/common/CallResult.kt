package com.c0de_h0ng.cleansample.common

/**
 * Created by c0de_h0ng on 2022/02/01.
 */
sealed class CallResult<out T : Any> {
    data class Success<out T : Any>(val data: T?) : CallResult<T>()
    data class Error(val exception: String?, val errorCode: Int = 500) : CallResult<Nothing>()
    data class Loading(val isLoading: Boolean) : CallResult<Nothing>()
}
