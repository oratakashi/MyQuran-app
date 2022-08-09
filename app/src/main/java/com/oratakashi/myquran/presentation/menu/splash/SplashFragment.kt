package com.oratakashi.myquran.presentation.menu.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oratakashi.myquran.R
import com.oratakashi.myquran.databinding.FragmentSplashBinding
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding

class SplashFragment : Fragment() {
    private val binding: FragmentSplashBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}