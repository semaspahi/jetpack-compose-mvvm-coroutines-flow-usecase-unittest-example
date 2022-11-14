package com.sema.data.model

import com.google.gson.annotations.SerializedName

class CarSearchResponse : ArrayList<CarSearchResponseItem>()

data class CarSearchResponseItem(
    @SerializedName("colour")
    val colour: String?,
    @SerializedName("description")
    val description: String,
    @SerializedName("firstRegistration")
    val firstRegistration: String?,
    @SerializedName("fuel")
    val fuel: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<Image>?,
    @SerializedName("make")
    val make: String,
    @SerializedName("mileage")
    val mileage: Int?,
    @SerializedName("model")
    val model: String,
    @SerializedName("modelline")
    val modelline: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("seller")
    val seller: Seller?
)

data class Image(
    @SerializedName("url")
    val url: String?
)

data class Seller(
    @SerializedName("city")
    val city: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("type")
    val type: String?
)