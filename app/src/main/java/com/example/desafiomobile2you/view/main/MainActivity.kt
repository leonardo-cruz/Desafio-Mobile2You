package com.example.desafiomobile2you.view.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafiomobile2you.R
import com.example.desafiomobile2you.model.Similar.SimilarMoviesResult
import com.example.desafiomobile2you.model.movieDetails.MovieDetails
import com.example.desafiomobile2you.util.Constantes.API_KEY
import com.example.desafiomobile2you.util.Constantes.URL_IMAGEM
import com.example.desafiomobile2you.util.voteFormated
import com.example.desafiomobile2you.view.main.adapter.MovieAdapter
import com.example.desafiomobile2you.view.main.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    lateinit var viewModel: MovieViewModel
    var id = 27205
    var favoriteStatus = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVars()
        initActions()
    }

    private fun initVars() {
        context = this
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        viewModel.mutableMovieDetails.observe(this, Observer { movie -> setView(movie) })
        returnApi()
        initRecycler()
    }

    private fun clickMovie(movie: SimilarMoviesResult) {
        id = movie.id
        favoriteStatus = 0
        iv_favorito.setImageResource(R.drawable.ic_favorite_white)
        returnApi()
    }

    fun returnApi() {
        viewModel.getSimilar(id, API_KEY)
        viewModel.getMovieDetails(id, API_KEY)
        viewModel.loading.observe(this, Observer {
            when {
                it -> {
                    progressBar.visibility = View.VISIBLE
                }
                else -> {
                    progressBar.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun initRecycler() {
        var movieList = mutableListOf<SimilarMoviesResult>()
        var adapter =
            MovieAdapter(
                movieList
            ) { movie ->
                clickMovie(movie)
            }
        recycler_movie.adapter = adapter
        recycler_movie.layoutManager = LinearLayoutManager(this)
        viewModel.mutableSimilarMovies.observe(
            this,
            Observer { similarMovies -> adapter.updateList(similarMovies as MutableList<SimilarMoviesResult>) })
    }

    private fun initActions() {
        iv_favorito.setOnClickListener { view ->
            favoriteStatus = if (favoriteStatus == 0) {
                iv_favorito.setImageResource(R.drawable.ic_favorite_red)
                1
            } else {
                iv_favorito.setImageResource(R.drawable.ic_favorite_white)
                0
            }
        }
    }

    private fun setView(movie: MovieDetails) {
        tv_titulo_detalhe.text = movie.title
        Picasso.get().load(URL_IMAGEM + movie.poster_path).into(iv_filme)
        tv_views.text = "${movie.popularity} ${getString(R.string.views)}"
        tv_like.text = voteFormated(movie.vote_count)
    }
}