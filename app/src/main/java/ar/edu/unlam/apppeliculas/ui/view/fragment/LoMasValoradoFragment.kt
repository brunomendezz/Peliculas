package ar.edu.unlam.apppeliculas.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import ar.edu.unlam.apppeliculas.data.adapter.MovieTopRatedAdapter
import ar.edu.unlam.apppeliculas.data.model.MovieModel
import ar.edu.unlam.apppeliculas.data.model.MovieProvider
import ar.edu.unlam.apppeliculas.databinding.FragmentLoMasValoradoBinding
import ar.edu.unlam.apppeliculas.ui.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso

class LoMasValorado : Fragment() {

    private lateinit var binding: FragmentLoMasValoradoBinding
    private val movieViewModel: MovieViewModel = MovieViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoMasValoradoBinding.inflate(layoutInflater)
        movieViewModel.onCreateMoviesTopRated()

        movieViewModel.isLoadingTopRated.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it
            if (it == false) {
                initRecyclerView()
            }
        })
        binding.detalle.toggleButton.setOnClickListener{
            binding.detalle.root.isVisible=false
        }

        return binding.root
    }
    private fun initRecyclerView() {
        val recyclerView = binding.rcvMoviesTopRated
        recyclerView.layoutManager = GridLayoutManager(this.context, 3)
        recyclerView.adapter = MovieTopRatedAdapter(MovieProvider.moviesTopRated){ onItemSelected(it)}
    }

    private fun onItemSelected(movie: MovieModel){
        binding.detalle.textView.text=movie.overview
        Picasso
            .get()
            .load("https://image.tmdb.org/t/p/original/${movie.backdrop_path}")
            .into(binding.detalle.imageViewDetail)
        binding.detalle.titulo.text=movie.title.uppercase()

        binding.detalle.root.isVisible=true

    }
}