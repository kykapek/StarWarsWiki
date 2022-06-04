package ru.kykapek.starwarswiki.models.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import ru.kykapek.starwarswiki.models.Film

@Parcelize
data class FilmResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("producer")
    val producer: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("characters")
    val characters: List<String>
) : Parcelable