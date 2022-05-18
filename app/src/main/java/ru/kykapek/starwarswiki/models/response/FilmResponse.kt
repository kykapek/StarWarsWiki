package ru.kykapek.starwarswiki.models.response

import com.google.gson.annotations.SerializedName
import ru.kykapek.starwarswiki.models.Film

data class FilmResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Film>
)