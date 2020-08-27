package com.example.desafiomobile2you.view.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafiomobile2you.model.Similar.SimilarMoviesResult
import com.example.desafiomobile2you.model.movieDetails.MovieDetails
import com.example.desafiomobile2you.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieViewModel() : ViewModel() {
    val disposable = CompositeDisposable()
    val mutableMovieDetails: MutableLiveData<MovieDetails> = MutableLiveData()
    val mutableSimilarMovies: MutableLiveData<List<SimilarMoviesResult?>> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()
    val repository = MovieRepository()

    fun getMovieDetails(id : Int, api: String){
        disposable.add(
            repository.getMovieDetails(id, api)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{ loading.value = true}
                .doAfterTerminate{ loading.value = false}
                .subscribe({movie -> mutableMovieDetails.value = movie}, {t -> error.value = t.message })
        )
    }

    fun getSimilar(id : Int, api: String){
        disposable.add(
            repository.getSimilarMovies(id, api)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({t -> mutableSimilarMovies?.value = t.results }, {t -> error.value = t.message })
        )
    }

}