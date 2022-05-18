package ru.kykapek.starwarswiki.models

import com.google.gson.annotations.SerializedName

data class Hero(
    @SerializedName("name")
    val name: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("birth_year")
    val birthday: String
)