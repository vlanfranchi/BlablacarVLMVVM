package com.jehutyno.blablacarmvvm.injection.component

import com.jehutyno.blablacarmvvm.injection.module.NetworkModule
import com.jehutyno.blablacarmvvm.ui.trips.TripsViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(tripsViewModel: TripsViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }

}