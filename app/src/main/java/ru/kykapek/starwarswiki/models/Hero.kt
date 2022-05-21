package ru.kykapek.starwarswiki.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "heroes")
data class Hero(
    val episode_id: Int,
    @PrimaryKey
    @SerializedName("name")
    val name: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("birth_year")
    val birthday: String
)