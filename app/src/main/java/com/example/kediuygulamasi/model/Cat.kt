package com.example.kediuygulamasi.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
@Entity
data class Cat(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val catName: String?,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val catDescription: String?,

    @ColumnInfo(name = "origin")
    @SerializedName("origin")
    val catOrigin: String?,

    @ColumnInfo(name = "wikipedia_url")
    @SerializedName("wikipedia_url")
    val catWikiUrl: String?,

    @ColumnInfo(name = "life_span")
    @SerializedName("life_span")
    val catLifeSpan: String?,

    @ColumnInfo(name = "dog_friendly")
    @SerializedName("dog_friendly")
    val catDogFriendly: String?,

    @ColumnInfo(name = "reference_image_id")
    @SerializedName("reference_image_id")
    val catReferenceImageId: String?,

    @ColumnInfo(name = "image")
    @SerializedName("image")
    val catImage: @RawValue Images,

    @ColumnInfo(name = "catIsFavorited")
    var catIsFavorited: Boolean?,

    ) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var catId: Int = 0
}