package com.oratakashi.myquran.presentation.navigation

import androidx.navigation.NavController
import com.oratakashi.myquran.R
import com.oratakashi.myquran.presentation.menu.splash.SplashFragmentDirections
import com.oratakashi.myquran.utility.getDefaultNavOptions

class MainNavigationController(
    private val navController: NavController?
): MainNavigation {
    override fun toMain() {
        runCatching {
            navController?.navigate(
                SplashFragmentDirections.actionSplashFragmentToMainFragment()
            )
        }.onFailure {
            it.printStackTrace()
            navController?.navigate(R.id.mainFragment, null, getDefaultNavOptions())
        }
    }
}