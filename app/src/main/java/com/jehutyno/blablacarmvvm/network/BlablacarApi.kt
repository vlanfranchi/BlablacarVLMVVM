package com.jehutyno.blablacarmvvm.network

import com.jehutyno.blablacarmvvm.model.Token
import com.jehutyno.blablacarmvvm.model.TokenRequest
import com.jehutyno.blablacarmvvm.model.Trips
import io.reactivex.Observable
import retrofit2.http.*

const val BASE_URL: String = "https://edge.blablacar.com"

interface BlablacarApi {

    @POST("/token")
    fun getToken(@Body request: TokenRequest): Observable<Token>

    @GET("/api/v2/trips")
    fun getTrips(@Header("Authorization") token: String, @Query("fn") departure: String, @Query("tn") destination: String): Observable<Trips>

}