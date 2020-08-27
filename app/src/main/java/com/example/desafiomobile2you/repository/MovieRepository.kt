package com.example.desafiomobile2you.repository

import com.example.desafiomobile2you.model.Similar.SimilarMovies
import com.example.desafiomobile2you.model.movieDetails.MovieDetails
import com.example.desafiomobile2you.network.RetrofitService
import io.reactivex.Observable
import io.reactivex.Single

open class MovieRepository {

    fun getMovieDetails(id : Int, api: String) : Single<MovieDetails>{
        return RetrofitService.service.getMovieDetails(id, api)
    }

    fun getSimilarMovies(id : Int, api: String) : Observable<SimilarMovies>{
        return RetrofitService.service.getSimilarMovies(id, api)
    }
}