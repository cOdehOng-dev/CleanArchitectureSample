package com.c0de_h0ng.cleansample.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.c0de_h0ng.cleansample.R
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observables.ConnectableObservable
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeViewModel()
        viewModel.getSearchResult("mike")
    }


    private fun observeViewModel() {
        viewModel.searchResult.observe(this) {
            Log.d("User Result Size ", it.size.toString())
        }
    }

}