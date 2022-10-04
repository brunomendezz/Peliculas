package ar.edu.unlam.apppeliculas.domain.model

import com.google.gson.annotations.SerializedName

data class MovieModel(@SerializedName("poster_path") var poster_path: String, @SerializedName("adult") var adult: Boolean,
                      @SerializedName("overview")var overview: String,
                      @SerializedName("release_date")var release_date: String,
                      @SerializedName("genre_ids") var genre_ids: List<Int>,
                      @SerializedName("id")var id: Int,
                      @SerializedName("original_title") var original_title: String,
                      @SerializedName("original_language")var original_language: String,
                      @SerializedName("title")var title: String,
                      @SerializedName("backdrop_path") var backdrop_path: String,
                      @SerializedName("popularity")var popularity: Number,
                      @SerializedName("vote_count") var vote_count: Number,
                      @SerializedName("video")var video: Boolean,
                      @SerializedName("vote_average")var vote_average: Number)
