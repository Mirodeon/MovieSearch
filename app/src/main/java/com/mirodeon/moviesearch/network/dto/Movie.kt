package com.mirodeon.moviesearch.network.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Results(
    @SerializedName("results")
    var results: List<Movie>
)

@Parcelize
data class Movie(
    @SerializedName("id")
    var id: Int,
    @SerializedName("original_title")
    var title: String,
    @SerializedName("poster_path")
    var poster: String?,
    @SerializedName("backdrop_path")
    var backdrop: String?,
    @SerializedName("release_date")
    var release: String,
    @SerializedName("vote_average")
    var vote: Float,
    @SerializedName("overview")
    var overview: String
) : Parcelable