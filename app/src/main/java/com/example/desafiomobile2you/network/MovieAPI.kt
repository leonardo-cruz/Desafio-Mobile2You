package com.example.desafiomobile2you.network

import com.example.desafiomobile2you.model.Similar.SimilarMovies
import com.example.desafiomobile2you.model.movieDetails.MovieDetails
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") api: String
    ): Single<MovieDetails>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(
        @Path("movie_id") id: Int,
        @Query("api_key") api: String
    ): Observable<SimilarMovies>
}