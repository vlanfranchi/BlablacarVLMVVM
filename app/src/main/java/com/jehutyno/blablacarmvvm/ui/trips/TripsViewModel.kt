package com.jehutyno.blablacarmvvm.ui.trips

import android.arch.lifecycle.MutableLiveData
import android.content.SharedPreferences
import android.view.View
import com.jehutyno.blablacarmvvm.base.BaseViewModel
import com.jehutyno.blablacarmvvm.converter.TripConverter
import com.jehutyno.blablacarmvvm.model.Token
import com.jehutyno.blablacarmvvm.model.TokenRequest
import com.jehutyno.blablacarmvvm.network.BlablacarApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject


class TripsViewModel(private val sharedPreferences: SharedPreferences) : BaseViewModel() {

    @Inject
    lateinit var blablacarApi: BlablacarApi

    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val tripsAdapter by lazy { TripsAdapter() }
    private var mAuthObservable: Observable<Token>

    init {
        mAuthObservable = authenticateUser(
            TokenRequest(
                "client_credentials",
                "android-technical-tests",
                "Y1oAL3QdPfVhGOWj3UeDjo3q02Qwhvrj"
            )
        )
            .doOnNext { response ->
                sharedPreferences.edit().putString("token", response.access_token).apply()
            }
            .share()
    }

    private fun getAuthObservable(): Observable<Token> {
        return if (sharedPreferences.getString("token", "").isNullOrEmpty()) {
            mAuthObservable
        } else {
            Observable.just<Token>(Token("", "", 0, 0, listOf()))
        }
    }


    private fun authenticateUser(request: TokenRequest): Observable<Token> {
        return blablacarApi.getToken(request)
    }



    fun loadTrips(departure: String, destination: String) {
        subscription = getAuthObservable().flatMap {
            blablacarApi.getTrips("Bearer ${sharedPreferences.getString("token", "")}", departure, destination)  }
                .retryWhen { errorObservable ->
                    errorObservable.zipWith(Observable.range(1, 3), BiFunction { error: Throwable, retryCount: Int -> Pair(error, retryCount) })
                    .flatMap {
                        val errorCode = (it.first as HttpException).code()
                        if (errorCode == 403 || errorCode == 401) {
                            sharedPreferences.edit().remove("token").apply()
                            mAuthObservable
                        } else {
                            Observable.error(it.first)
                        }
                    }
                }
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
        println("Problemos: ${error.message}")
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}