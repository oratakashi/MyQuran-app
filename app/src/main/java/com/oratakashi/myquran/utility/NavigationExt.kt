package com.oratakashi.myquran.utility

import android.app.Activity
import android.content.ComponentCallbacks
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.agree.ecosystem.core.utils.R
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

inline fun <reified T : Any> ComponentCallbacks.navigation(
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED,
    crossinline activity: () -> Activity?
) = lazy(mode) {
    inject<T> { parametersOf(activity.invoke()) }.value
}

fun Fragment.navHost(@IdRes id: Int): NavHostFragment {
    return childFragmentManager.findFragmentById(id) as NavHostFragment
}

fun FragmentActivity.navHost(@IdRes id: Int): NavHostFragment {
    return supportFragmentManager.findFragmentById(id) as NavHostFragment
}

fun getDefaultNavOptions(): NavOptions {
    return NavOptions.Builder()
        .setEnterAnim(R.anim.anim_slide_in_right)
        .setExitAnim(R.anim.anim_slide_out_left)
        .setPopEnterAnim(R.anim.anim_slide_in_right)
        .setPopExitAnim(R.anim.anim_slide_out_left)
        .build()
}
