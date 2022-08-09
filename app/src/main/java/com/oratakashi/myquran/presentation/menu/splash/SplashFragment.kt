package com.oratakashi.myquran.presentation.menu.splash

import androidx.lifecycle.lifecycleScope
import com.oratakashi.myquran.databinding.FragmentSplashBinding
import com.oratakashi.myquran.presentation.abstaction.BaseFragment
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override val binding: FragmentSplashBinding by viewBinding()

    override fun initAction() {
        super.initAction()
        lifecycleScope.launch {
            delay(2000)
            //Navigate to Main
        }
    }
}