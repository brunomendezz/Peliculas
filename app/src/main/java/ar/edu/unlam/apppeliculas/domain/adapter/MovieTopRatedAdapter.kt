package ar.edu.unlam.apppeliculas.domain.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unlam.apppeliculas.R
import ar.edu.unlam.apppeliculas.domain.model.MovieModel

class MovieTopRatedAdapter(private val movieList: List<MovieModel>, private val onClickListener:(MovieModel)->Unit): RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return MovieViewHolder(layoutInflater.inflate(R.layout.item_movie,parent,false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = movieList[position]
        holder.render(item,onClickListener)
    }

    override fun getItemCount(): Int = movieList.size

}