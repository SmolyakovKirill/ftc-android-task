package com.example.cardapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardapp.data.repository.Repository
import com.example.cardapp.model.CardItem
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel: ViewModel() {
    var repo = Repository()
    val myCardItem: MutableLiveData<Response<CardItem>> = MutableLiveData()

    fun getCardInformation(cardBind: String){
        viewModelScope.launch {
            myCardItem.value = repo.getCardInform(cardBind)
        }
    }
}