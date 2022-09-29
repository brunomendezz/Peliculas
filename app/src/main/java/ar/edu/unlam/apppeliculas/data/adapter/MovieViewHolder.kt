package ar.edu.unlam.apppeliculas.data.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unlam.apppeliculas.data.model.MovieModel
import ar.edu.unlam.apppeliculas.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {


    private var binding = ItemMovieBinding.bind(view)
    fun render(movieModel: MovieModel, onClickListener:(MovieModel)->Unit){

        Picasso
            .get()
            .load("https://image.tmdb.org/t/p/original/${movieModel.poster_path}")
            .into(binding.ivMovie)
        binding.ivMovie.setOnClickListener{
            onClickListener(movieModel)
        }
    }
}