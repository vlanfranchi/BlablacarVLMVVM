package com.jehutyno.blablacarmvvm.base

import android.arch.lifecycle.ViewModel
import com.jehutyno.blablacarmvvm.injection.component.DaggerViewModelInjector
import com.jehutyno.blablacarmvvm.injection.component.ViewModelInjector
import com.jehutyno.blablacarmvvm.injection.module.NetworkModule
import com.jehutyno.blablacarmvvm.ui.trips.TripsViewModel

abstract class BaseViewModel: ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

        init {
            inject()
        }


    private fun inject() {
        when(this) {
            is TripsViewModel -> injector.inject(this)
        }
    }

}