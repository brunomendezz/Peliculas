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
        val listaPopularMovies : MutableList<MovieModel> = mutableListOf()
        val listaTopRatedMovies : MutableList<MovieModel> = mutableListOf()
        val listaTrendingMovies : MutableList<MovieModel> = mutableListOf()

        val layoutManagerr = GridLayoutManager(this.context, 1, GridLayoutManager.HORIZONTAL, false)

        var adapterPopular =  MoviePopularAdapter(listaPopularMovies){onItemSelected(it)}
        recyclerView1.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                movieViewModel.lastPopularVisible.value = layoutManagerr.findLastVisibleItemPosition()
            }
        })
        recyclerView1.adapter=adapterPopular
        recyclerView1.layoutManager = layoutManagerr

        movieViewModel.moviesPopular.observe(viewLifecycleOwner, Observer {
            it?.forEach {
                if (!listaPopularMovies.contains(it)){
                    listaPopularMovies.add(it)
                    adapterPopular.notifyItemInserted(listaPopularMovies.size)
                }
            }
        })

           val adapterTopRated = MovieTopRatedAdapter(listaTopRatedMovies) { onItemSelected(it) }
        val layoutManager2 = GridLayoutManager(this.context, 1, GridLayoutManager.HORIZONTAL, false)
        recyclerView2.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                movieViewModel.lastTopRatedVisible.value = layoutManager2.findLastVisibleItemPosition()
            }
        })
        recyclerView2.adapter=adapterTopRated
        recyclerView2.layoutManager=layoutManager2

        movieViewModel.moviesTopRated.observe(viewLifecycleOwner, Observer {
            it?.forEach {
                if (!listaTopRatedMovies.contains(it)){
                    listaTopRatedMovies.add(it)
                    adapterTopRated.notifyItemInserted(listaTopRatedMovies.size)
                }
            }
        })





       val adapterTrending =
            MovieTrendingAdapter(listaTrendingMovies) { onItemSelected(it) }
        val layoutManager3 = GridLayoutManager(this.context, 1, GridLayoutManager.HORIZONTAL, false)
        recyclerView3.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                movieViewModel.lastTrendingVisible.value = layoutManager3.findLastVisibleItemPosition()
            }
        })
        recyclerView3.adapter=adapterTrending
        recyclerView3.layoutManager=layoutManager3

        movieViewModel.moviesTrending.observe(viewLifecycleOwner, Observer {
            it?.forEach {
                if (!listaTrendingMovies.contains(it)){
                    listaTrendingMovies.add(it)
                        adapterTrending.notifyItemInserted(listaTrendingMovies.size)
                }
            }
        })
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

