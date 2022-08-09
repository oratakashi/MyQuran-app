package com.oratakashi.myquran.presentation.menu.splash

import androidx.lifecycle.lifecycleScope
import com.oratakashi.myquran.databinding.FragmentSplashBinding
import com.oratakashi.myquran.presentation.abstaction.BaseFragment
import com.oratakashi.myquran.presentation.navigation.MainNavigation
import com.oratakashi.myquran.utility.navigation
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override val binding: FragmentSplashBinding by viewBinding()

    private val nav: MainNavigation by navigation { activity }

    override fun initAction() {
        super.initAction()
        lifecycleScope.launch {
            delay(2000)
            nav.toMain()
        }
    }
}