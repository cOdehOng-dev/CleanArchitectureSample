package com.c0de_h0ng.cleansample.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.c0de_h0ng.cleansample.R
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.Single

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}