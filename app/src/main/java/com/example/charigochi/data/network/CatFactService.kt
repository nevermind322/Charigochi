package com.example.charigochi.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val CAT_FACT_URL = "https://meowfacts.herokuapp.com/"

val retrofit =
    Retrofit.Builder().baseUrl(CAT_FACT_URL).addConverterFactory(GsonConverterFactory.create())
        .build()
val service = retrofit.create(CatFactService::class.java)

interface CatFactService {
    @GET(".")
    suspend fun getFacts(@Query("lang") language: String): ApiResponse
}

data class ApiResponse(val data: List<String>)