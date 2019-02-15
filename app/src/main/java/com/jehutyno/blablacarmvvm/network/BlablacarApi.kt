package com.jehutyno.blablacarmvvm.network

import com.jehutyno.blablacarmvvm.model.Token
import com.jehutyno.blablacarmvvm.model.Trips
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

const val BASE_URL: String = "https://edge.blablacar.com"

interface BlablacarApi {

    @POST("/token")
    fun getToken(): Observable<Token>

    @GET("/api/v2/trips")
    fun getTrips(@Query("fn") departure: String, @Query("tn") destination: String): Observable<Trips>

}