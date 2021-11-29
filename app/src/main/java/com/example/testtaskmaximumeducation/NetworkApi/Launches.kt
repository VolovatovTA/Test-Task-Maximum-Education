package com.example.testtaskmaximumeducation.NetworkApi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Launches(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("provider")
    @Expose
    val provider: String
)
