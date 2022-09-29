package ar.edu.unlam.apppeliculas.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.apppeliculas.data.model.MovieModel
import ar.edu.unlam.apppeliculas.domain.GetMovies
import ar.edu.unlam.apppeliculas.domain.GetMoviesTopRated
import ar.edu.unlam.apppeliculas.domain.GetMoviesTrending
import kotlinx.coroutines.launch

    class MovieViewModel : ViewModel() {
        var isLoading = MutableLiveData<Boolean>()
        var isLoadingTrending = MutableLiveData<Boolean>()
        var isLoadingTopRated = MutableLiveData<Boolean>()
        var movieADetail = MutableLiveData<MovieModel>()

        var getMoviesUseCase = GetMovies()
        var getMoviesTrending = GetMoviesTrending()
        var getMoviesTopRated = GetMoviesTopRated()


        fun onCreate() {
            viewModelScope.launch {
                isLoading.postValue(true)
                val result = getMoviesUseCase()
                if (!result.isNullOrEmpty()) {
                    isLoading.postValue(false)
                }
            }
        }

        fun onCreateMoviesTrending() {
            viewModelScope.launch {
                isLoadingTrending.postValue(true)
                val result = getMoviesTrending()
                if (!result.isNullOrEmpty()) {
                    isLoadingTrending.postValue(false)
                }
            }
        }

        fun onCreateMoviesTopRated() {
            viewModelScope.launch {
                isLoadingTopRated.postValue(true)
                val result = getMoviesTopRated()
                if (!result.isNullOrEmpty()) {
                    isLoadingTopRated.postValue(false)
                }
            }
        }

        fun seTocoEstaMovie(movie: MovieModel) {
            viewModelScope.launch {
                movieADetail.postValue(movie)
            }

        }
    }