package com.example.desafiomobile2you.model.Similar

data class SimilarMovies(
    val page: Int,
    val results: List<SimilarMoviesResult>,
    val total_pages: Int,
    val total_results: Int
)