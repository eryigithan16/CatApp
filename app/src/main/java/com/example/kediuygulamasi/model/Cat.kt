package com.example.kediuygulamasi.model

data class Cat( //dummy data'lar için bir model oluşturdum. MVVM'i denemek için, yarın devam..
    val catName: String?,
    val catDescription: String?,
    val catOrigin: String?,
    val catWikiUrl: String?,
    val catLifeSpan: String?,
    val catDogFriendly: String?,
    val catImageUrl: String?,
    val catIsFavorited: Boolean?,
)