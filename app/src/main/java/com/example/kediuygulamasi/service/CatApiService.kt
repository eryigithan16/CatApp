package com.example.kediuygulamasi.service

import com.example.kediuygulamasi.model.Cat
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CatApiService {

    private val BASE_URL = "https://api.thecatapi.com/"
    private val API_KEY = "824bd368-5bee-40a7-9deb-f0191db337f2"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CatAPI::class.java)

    fun getCatListDataFromApi() : Single<List<Cat>> {
        return api.getDataList(API_KEY)
    }

    fun getSearchedCatListFromApi(searchText : String?) : Single<List<Cat>> {
        return api.getDataSearchList(API_KEY, searchText!!)
    }
}