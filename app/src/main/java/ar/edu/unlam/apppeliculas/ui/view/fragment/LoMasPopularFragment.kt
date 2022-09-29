package ar.edu.unlam.apppeliculas.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.PointerIconCompat.load
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import ar.edu.unlam.apppeliculas.R
import ar.edu.unlam.apppeliculas.data.adapter.MovieAdapter
import ar.edu.unlam.apppeliculas.data.model.MovieModel
import ar.edu.unlam.apppeliculas.data.model.MovieProvider
import ar.edu.unlam.apppeliculas.databinding.ActivityMainBinding
import ar.edu.unlam.apppeliculas.databinding.FragmentDetailMovieBinding
import ar.edu.unlam.apppeliculas.databinding.FragmentLoMasPopularBinding
import ar.edu.unlam.apppeliculas.ui.viewmodel.MovieViewModel
import com.google.android.material.navigation.NavigationView
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import com.squareup.picasso.Picasso
import java.lang.System.load

class LoMasPopularFragment : Fragment() {
    private lateinit var binding: FragmentLoMasPopularBinding
    private val movieViewModel: MovieViewModel = MovieViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoMasPopularBinding.inflate(layoutInflater)
        movieViewModel.onCreate()
        movieViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it
            if (it == false) {
                initRecyclerView()
            }
        })

        return binding.root
    }

    private fun initRecyclerView() {
        val recyclerView = binding.rcvMovies
        recyclerView.layoutManager = GridLayoutManager(this.context, 3)
        recyclerView.adapter = MovieAdapter(MovieProvider.movies) { onItemSelected(it) }
    }

    private fun onItemSelected(movie: MovieModel) {
        val bundle = Bundle()
        bundle.putString("movie", GsonBuilder().create().toJson(movie))
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_loMasPopularFragment_to_detailMovieFragment, bundle)

        }

    }
}


