package com.example.cardapp.data.repository

import com.example.cardapp.data.api.RetrofitInstance
import com.example.cardapp.model.CardItem
import retrofit2.Response

class Repository {
    suspend fun getCardInform(cardBin: String): Response<CardItem>{
        return RetrofitInstance.api.getCardInformation(cardBin)
    }
}