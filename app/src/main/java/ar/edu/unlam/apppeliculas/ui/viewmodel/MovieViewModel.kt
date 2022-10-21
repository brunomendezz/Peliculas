package ar.edu.unlam.apppeliculas.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.apppeliculas.data.MovieProvider
import ar.edu.unlam.apppeliculas.domain.model.MovieModel
import ar.edu.unlam.apppeliculas.domain.usecase.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieViewModel() : ViewModel() {
    private var _isLoading = MutableLiveData<Boolean>()

    var moviesPopular = MutableLiveData<List<MovieModel>?>()
    var moviesTopRated = MutableLiveData<List<MovieModel>?>()
    var moviesTrending = MutableLiveData<List<MovieModel>?>()

    private var getMoviesUseCase = GetMovies()
    private var getMoreMoviesPopular = GetMoreMoviesPopular()
    private var getMoreMoviesTrending = GetMoreMoviesTrending()
    private var getMoreMoviesTopRated = GetMoreMoviesTopRated()
    private var getMoviesTrending = GetMoviesTrending()
    private var getMoviesTopRated = GetMoviesTopRated()




    val isLoading: LiveData<Boolean> get() = _isLoading
    val lastPopularVisible = MutableStateFlow(0)
    val lastTrendingVisible = MutableStateFlow(0)
    val lastTopRatedVisible = MutableStateFlow(0)



    fun onCreate() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val result = getMoviesUseCase()
            moviesPopular.postValue(result)
            val result2 = getMoviesTrending()
            moviesTrending.postValue(result2)
            val result3 = getMoviesTopRated()
            moviesTopRated.postValue(result3)

            if (!result.isNullOrEmpty() && !result2.isNullOrEmpty() && !result3.isNullOrEmpty()) {
                _isLoading.postValue(false)
            }
        }
    }

    init {
        viewModelScope.launch{
        lastTrendingVisible.collect{ notifyLastTrendingVisible(it) }
      }
    }

    init {
        viewModelScope.launch {
            lastPopularVisible.collect { notifyLastPopularVisible(it) }
        }
    }

    init {
        viewModelScope.launch {
            lastTopRatedVisible.collect{ notifyLastTopRatedVisible(it) }
        }
    }

    suspend fun notifyLastPopularVisible(position: Int) {

        viewModelScope.launch {
          val result = getMoreMoviesPopular(position)
            if(!result.isNullOrEmpty()){
                moviesPopular.postValue(result)
            }
        }
    }

    suspend fun notifyLastTrendingVisible(position: Int) {

        viewModelScope.launch {
           val result = getMoreMoviesTrending(position)
            if (!result.isNullOrEmpty()){
                moviesTrending.postValue(result)
            }
        }
    }

    suspend fun notifyLastTopRatedVisible(position: Int) {

        viewModelScope.launch {
           val result = getMoreMoviesTopRated(position)
            if (!result.isNullOrEmpty()){
                moviesTopRated.postValue(result)
            }
        }
    }
}

