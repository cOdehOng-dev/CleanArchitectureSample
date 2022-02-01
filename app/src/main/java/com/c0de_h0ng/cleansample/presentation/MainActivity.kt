package com.c0de_h0ng.cleansample.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.c0de_h0ng.cleansample.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeViewModel()
        viewModel.getSearchResult("mike") // Coroutine
        viewModel.getRxSearchResult("mike") // RxJava
    }

    private fun observeViewModel() {
        with(viewModel) {
            // Coroutine
            searchResult.observe(this@MainActivity) {
                Log.d("CoroutineResult Size", it.count().toString())
            }
            // RxJava
            rxSearchResult.observe(this@MainActivity) {
                Log.d("RxJavaResult Size", it.count().toString())
            }
        }
    }

}