package com.example.kediuygulamasi.service

import com.example.kediuygulamasi.model.Cat
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatAPI {

    @GET("v1/breeds")
    fun getDataList(@Header("x-api-key") apiKey : String)
    : Single<List<Cat>>

    @GET("v1/breeds/search")
    fun getDataSearchList(@Header("x-api-key") apiKey: String, @Query("q") q: String)
    : Single<List<Cat>>
}