package com.example.desafiomobile2you.view.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiomobile2you.R
import com.example.desafiomobile2you.model.Similar.SimilarMoviesResult
import com.example.desafiomobile2you.util.Constantes
import com.example.desafiomobile2you.util.yearFormated
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_list.view.*

class MovieAdapter(
    var movieList: MutableList<SimilarMoviesResult>,
    val clickListener: (SimilarMoviesResult) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_list, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(movieList[position])
        holder.itemView.setOnClickListener { clickListener(movieList[position]) }
    }

    fun updateList(newList: MutableList<SimilarMoviesResult>) {
        this.movieList.removeAll(movieList)
        this.movieList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(movie: SimilarMoviesResult) {
            Picasso.get().load(Constantes.URL_IMAGEM + movie.poster_path)
                .into(itemView.iv_poster_recycler)
            itemView.tv_title_list.text = movie.title
            itemView.tv_year_recycler.text = yearFormated(movie.release_date)
        }
    }
}