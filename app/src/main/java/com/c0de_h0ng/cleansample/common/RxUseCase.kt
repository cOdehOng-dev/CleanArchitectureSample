package com.c0de_h0ng.cleansample.common

import androidx.lifecycle.MutableLiveData

/**
 * Created by c0de_h0ng on 2022/02/01.
 */
abstract class RxUseCase<R: Any> {
    protected val result = MutableLiveData<CallResult<R>>()
    fun observe() = result
}