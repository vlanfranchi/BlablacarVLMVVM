package com.jehutyno.blablacarmvvm.ui.trips

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.jehutyno.blablacarmvvm.base.BaseViewModel
import com.jehutyno.blablacarmvvm.converter.TripConverter
import com.jehutyno.blablacarmvvm.model.Token
import com.jehutyno.blablacarmvvm.model.TokenRequest
import com.jehutyno.blablacarmvvm.network.BlablacarApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class TripsViewModel : BaseViewModel() {

    @Inject
    lateinit var blablacarApi: BlablacarApi

    var token: String? = null

    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val tripsAdapter by lazy { TripsAdapter() }
    private var mAuthObservable: Observable<Token>
    //val preferences by lazy { app.getSharedPreferences("session_prefs", Context.MODE_PRIVATE) }

    init {
        mAuthObservable = authenticateUser(
            TokenRequest(
                "client_credentials",
                "android-technical-tests",
                "Y1oAL3QdPfVhGOWj3UeDjo3q02Qwhvrj"
            )
        )
            .doOnNext { response ->
                token = response.access_token
            }
            .share()
        loadTrips()
    }

    private fun getAuthObservable(): Observable<Token> {
        return if (token.isNullOrEmpty()) {
            mAuthObservable
        } else {
            Observable.just<Token>(Token("", "", 0, 0, listOf()))
        }
    }


    private fun authenticateUser(request: TokenRequest): Observable<Token> {
        return blablacarApi.getToken(request)
    }


    private fun loadTrips() {
        subscription =
            getAuthObservable().flatMap { blablacarApi.getTrips("Bearer $token","paris", "marseille") }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveTripsStart() }
                .doOnTerminate { onRetrieveTripsFinish() }
                .subscribe(
                    { result -> onRetrieveTripsSuccess(TripConverter.convert(result.trips)) },
                    { error -> onRetrieveTripsError(error) }
                )
    }

    private fun onRetrieveTripsStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrieveTripsFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveTripsSuccess(tripItem: List<TripItem>?) {
        tripsAdapter.update(tripItem)
    }

    private fun onRetrieveTripsError(error: Throwable) {
        println("Problemos: ${error.message}" )
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}