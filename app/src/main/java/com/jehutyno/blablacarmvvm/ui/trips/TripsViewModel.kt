package com.jehutyno.blablacarmvvm.ui.trips

import com.jehutyno.blablacarmvvm.base.BaseViewModel
import com.jehutyno.blablacarmvvm.network.BlablacarApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TripsViewModel: BaseViewModel() {

    @Inject
    lateinit var blablacarApi: BlablacarApi

    private lateinit var  subscription: Disposable

    init {
        loadTrips()
    }

    private fun loadTrips() {
        subscription = blablacarApi.getTrips("paris", "marseille")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveTripsStart() }
            .doOnTerminate { onRetrieveTripsFinish() }
            .subscribe(
                { onRetrieveTripsSuccess() },
                { onRetrieveTripsError() }
            )
    }

    private fun onRetrieveTripsStart(){

    }

    private fun onRetrieveTripsFinish(){

    }

    private fun onRetrieveTripsSuccess(){

    }

    private fun onRetrieveTripsError(){

    }

}