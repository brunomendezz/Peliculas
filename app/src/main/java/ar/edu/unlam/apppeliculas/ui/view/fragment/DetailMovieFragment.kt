package ar.edu.unlam.apppeliculas.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import ar.edu.unlam.apppeliculas.domain.model.MovieModel
import ar.edu.unlam.apppeliculas.databinding.FragmentDetailMovieBinding
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso

class DetailMovieFragment : Fragment() {
    private lateinit var binding: FragmentDetailMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailMovieBinding.inflate(layoutInflater)
        val movie =
            GsonBuilder().create().fromJson(arguments?.getString("movie"), MovieModel::class.java)
        binding.fecha.text=movie.release_date

        if(movie.adult){
            binding.adult.text="+18"
        } else {
            binding.adult.text="ATP"
        }
        binding.lenguaje.text = "Lenguaje original: ${movie.original_language}"
        binding.titulo.text = movie.title
        Picasso
            .get()
            .load("https://image.tmdb.org/t/p/original/${movie.backdrop_path}")
            .into(binding.imageViewDetail)
        binding.overview.text=movie.overview
        binding.ratingBar.rating= movie.vote_average.toFloat()


        binding.toggleButton.setOnClickListener {
            it.isVisible = false
        }
        return binding.root
    }

}