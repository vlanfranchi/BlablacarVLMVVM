package com.jehutyno.blablacarmvvm.ui.trips

import com.jehutyno.blablacarmvvm.base.BaseViewModel
import com.jehutyno.blablacarmvvm.network.BlablacarApi
import javax.inject.Inject

class TripsViewModel: BaseViewModel() {

    @Inject
    lateinit var blablacarApi: BlablacarApi

}