package com.c0de_h0ng.cleansample.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c0de_h0ng.cleansample.common.Resource
import com.c0de_h0ng.cleansample.domain.model.User
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
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _searchResult = MutableLiveData<List<User>>()
    val searchResult: LiveData<List<User>>
        get() = _searchResult


    fun getSearchResult(searchWord: String) {
        getUserUseCase(searchWord).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _searchResult.value = result.data
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
        }.launchIn(viewModelScope)
    }
}