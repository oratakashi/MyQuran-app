package com.oratakashi.myquran.di

import android.app.Activity
import androidx.navigation.findNavController
import com.oratakashi.myquran.R
import com.oratakashi.myquran.presentation.navigation.MainNavigation
import com.oratakashi.myquran.presentation.navigation.MainNavigationController
import org.koin.dsl.module

val navigationModule = module {
    factory<MainNavigation> { (activity: Activity?) ->
        MainNavigationController(activity?.findNavController(R.id.nav_host_fragment_main))
    }
}