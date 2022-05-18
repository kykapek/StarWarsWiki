package ru.kykapek.starwarswiki.models

import com.google.gson.annotations.SerializedName

data class Planet(
    @SerializedName("name")
    val name: String
)
