package com.example.cardapp.data.api

import com.example.cardapp.model.CardItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{cardBin}")

    suspend fun getCardInformation(@Path("cardBin") cardBin: String):Response<CardItem>
}