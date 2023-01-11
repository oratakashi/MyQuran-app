package com.oratakashi.myquran.utils.di

import android.content.Context
import androidx.startup.Initializer
import org.koin.core.module.Module


class CoreUtilsModule : Initializer<Array<Module>>, NetworkModules {
    override fun create(context: Context): Array<Module> {
        return arrayOf(
            *provideNetworkModules()
        )
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}