package com.example.kediuygulamasi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kediuygulamasi.model.Cat

class DetailViewModel : ViewModel() {

    val cat = MutableLiveData<Cat>()

    fun getDataFromRoom(argCat : Cat){ //bunun ismi bu olmıcak aslında, sonra değiştir çünkü bu fragmentta roomdan çekmiyoruz (favoritede roomdan çekiyoruz)
        cat.value = argCat
    }
}