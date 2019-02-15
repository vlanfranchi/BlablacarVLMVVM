package com.jehutyno.blablacarmvvm.network

import com.jehutyno.blablacarmvvm.model.Token
import com.jehutyno.blablacarmvvm.model.Trips
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST

const val BASE_URL: String = "https://edge.blablacar.com"

interface BlablacarAPI {

    @POST("/token")
    fun getToken(): Observable<Token>

    @GET("/api/v2/trips")
    fun getTrips(departure: String, destination: String): Observable<Trips>

}