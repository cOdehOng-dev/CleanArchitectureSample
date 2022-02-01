package com.c0de_h0ng.cleansample.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.c0de_h0ng.cleansample.common.BaseViewModel
import com.c0de_h0ng.cleansample.common.CallResult
import com.c0de_h0ng.cleansample.data.remote.dto.toUserList
import com.c0de_h0ng.cleansample.domain.model.User
import com.c0de_h0ng.cleansample.domain.usecase.GetRxUserUseCase
import com.c0de_h0ng.cleansample.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by c0de_h0ng on 2022/01/21.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getRxUserUseCase: GetRxUserUseCase
) : BaseViewModel() {

    private val _searchResult = MutableLiveData<List<User>>()
    val searchResult: LiveData<List<User>>
        get() = _searchResult

    private val _rxSearchResult = MediatorLiveData<List<User>>()
    val rxSearchResult: LiveData<List<User>>
        get() = _rxSearchResult

    private val rxSearchResultObserve = getRxUserUseCase.observe()


    fun getSearchResult(searchWord: String) {
        getUserUseCase(searchWord).onEach { result ->
            when (result) {
                is CallResult.Success -> {
                    Log.d("CoroutineCallResult ", "Success")
                    _searchResult.value = result.data ?: emptyList()
                }
                is CallResult.Error -> {
                    Log.d("CoroutineCallResult ", "Error")

                }
                is CallResult.Loading -> {
                    Log.d("CoroutineCallResult ", "Loading")

                }
            }
        }.launchIn(viewModelScope)
    }

    fun getRxSearchResult(searchWord: String) {
        this(getRxUserUseCase(searchWord))
        _rxSearchResult.addSource(rxSearchResultObserve) {
            when (it) {
                is CallResult.Success -> {
                    Log.d("RxJavaCallResult ", "Success")
                    val user = it.data
                    _rxSearchResult.value = user?.toUserList()
                }
                is CallResult.Error -> {
                    Log.d("RxJavaCallResult ", "Error")
                }
                is CallResult.Loading -> {
                    Log.d("RxJavaCallResult ", "Loading")
                }
            }
        }
    }




}