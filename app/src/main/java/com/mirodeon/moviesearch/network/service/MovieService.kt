package com.mirodeon.moviesearch.network.service

import com.mirodeon.moviesearch.network.utils.UrlApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @Headers("Content-type: application/json")
    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query", encoded = true) query: String,
        @Query("api_key", encoded = true) apiKey: String
    ): Response<Any>

    @Headers("Content-type: application/json")
    @GET("movie/{movie_id}/similar")
    suspend fun similarMovie(
        @Path("movie_id") id: String,
        @Query("api_key", encoded = true) apiKey: String
    ): Response<Any>

    @Headers("Content-type: application/json")
    @GET("trending/all/day")
    suspend fun trendMovie(
        @Query("api_key", encoded = true) apiKey: String
    ): Response<Any>

}

class MovieServiceImpl : BaseService(UrlApi.movieApi) {
    suspend fun getSearchMovie(query: String): Response<Any> =
        getRetrofit().create(MovieService::class.java).searchMovie(query, "")

    suspend fun getSimilarMovie(id: String): Response<Any> =
        getRetrofit().create(MovieService::class.java).similarMovie(id, "")

    suspend fun getTrendMovie(): Response<Any> =
        getRetrofit().create(MovieService::class.java).trendMovie("")
}