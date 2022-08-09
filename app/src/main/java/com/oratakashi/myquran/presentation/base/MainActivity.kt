package com.oratakashi.myquran.presentation.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oratakashi.myquran.R
import com.oratakashi.myquran.databinding.ActivityMainBinding
import com.oratakashi.viewbinding.core.binding.activity.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {

        }
    }
}