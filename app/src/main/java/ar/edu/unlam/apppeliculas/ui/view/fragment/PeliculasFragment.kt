package ar.edu.unlam.apppeliculas.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unlam.apppeliculas.R
import ar.edu.unlam.apppeliculas.data.MovieProvider
import ar.edu.unlam.apppeliculas.databinding.FragmentPeliculasBinding
import ar.edu.unlam.apppeliculas.domain.adapter.MoviePopularAdapter
import ar.edu.unlam.apppeliculas.domain.adapter.MovieTopRatedAdapter
import ar.edu.unlam.apppeliculas.domain.adapter.MovieTrendingAdapter
import ar.edu.unlam.apppeliculas.domain.model.MovieModel
import ar.edu.unlam.apppeliculas.domain.usecase.GetMoviesTrending
import ar.edu.unlam.apppeliculas.ui.viewmodel.MovieViewModel
import com.google.gson.GsonBuilder

class PeliculasFragment : Fragment() {
    private lateinit var binding: FragmentPeliculasBinding
    private val movieViewModel: MovieViewModel = MovieViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPeliculasBinding.inflate(layoutInflater)

        if (MovieProvider.movies.isEmpty() || MovieProvider.moviesTopRated.isEmpty() || MovieProvider.moviesTrending.isEmpty()) {
            movieViewModel.onCreate()
        } else {
            initRecyclerView()
        }

        movieViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it
            if (it == false) {
                initRecyclerView()
            }
        })



        return binding.root
    }

    private fun initRecyclerView() {

        val recyclerView1 = binding.rcvMoviesLoMasPopular
        val recyclerView2 = binding.rcvMoviesLoMasValorado
        val recyclerView3 = binding.rcvMoviesTrending


        val layoutManagerr = GridLayoutManager(this.context, 1, GridLayoutManager.HORIZONTAL, false)

    val adapterPopular = MoviePopularAdapter(MovieProvider.movies) { onItemSelected(it) }
    recyclerView1.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            movieViewModel.lastPopularVisible.value = layoutManagerr.findLastVisibleItemPosition()
        }
    })
    recyclerView1.adapter=adapterPopular
    recyclerView1.layoutManager = layoutManagerr




           val adapterTopRated = MovieTopRatedAdapter(MovieProvider.moviesTopRated) { onItemSelected(it) }
        val layoutManager2 = GridLayoutManager(this.context, 1, GridLayoutManager.HORIZONTAL, false)
        recyclerView2.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                movieViewModel.lastTopRatedVisible.value = layoutManager2.findLastVisibleItemPosition()
            }
        })
        recyclerView2.adapter=adapterTopRated
        recyclerView2.layoutManager=layoutManager2


       val adapterTrending =
            MovieTrendingAdapter(MovieProvider.moviesTrending) { onItemSelected(it) }
        val layoutManager3 = GridLayoutManager(this.context, 1, GridLayoutManager.HORIZONTAL, false)
        recyclerView3.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                movieViewModel.lastTrendingVisible.value = layoutManager3.findLastVisibleItemPosition()
            }
        })
        recyclerView3.adapter=adapterTrending
        recyclerView3.layoutManager=layoutManager3

    }

    private fun onItemSelected(movie: MovieModel) {
        val bundle = Bundle()
        bundle.putString("movie", GsonBuilder().create().toJson(movie))
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_peliculasFragment_to_detailMovieFragment, bundle)
        }

    }
}

