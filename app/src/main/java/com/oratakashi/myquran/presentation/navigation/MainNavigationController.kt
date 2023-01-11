package com.oratakashi.myquran.presentation.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.oratakashi.myquran.R
import com.oratakashi.myquran.domain.model.surah.Surah
import com.oratakashi.myquran.presentation.menu.main.MainFragmentDirections
import com.oratakashi.myquran.presentation.menu.splash.SplashFragmentDirections
import com.oratakashi.myquran.utils.getDefaultNavOptions

class MainNavigationController(
    private val navController: NavController?
) : MainNavigation {
    override fun toPrevious() {
        navController?.navigateUp()
    }

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

    override fun toDetail(surah: Surah) {
        runCatching {
            navController?.navigate(
                MainFragmentDirections.actionMainFragmentToDetailFragment(surah)
            )
        }.onFailure {
            it.printStackTrace()
            navController?.navigate(
                R.id.detailFragment,
                bundleOf("data" to surah),
                getDefaultNavOptions()
            )
        }
    }
}