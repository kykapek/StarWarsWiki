package ru.kykapek.starwarswiki.models.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import ru.kykapek.starwarswiki.models.Film

@Parcelize
data class FilmResponse(
    @SerializedName("characters")
    val results: String
) : Parcelable