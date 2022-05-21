package ru.kykapek.starwarswiki.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "films")
@Parcelize
data class Film(
    @PrimaryKey
    @SerializedName("title")
    val title: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("producer")
    val producer: String,
    @SerializedName("release_date")
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    val releaseDate: String,
    @SerializedName("characters")
    val characters: List<String>
) : Parcelable
