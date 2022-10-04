package ar.edu.unlam.apppeliculas.ui.viewmodel

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.apppeliculas.domain.model.MovieModel
import ar.edu.unlam.apppeliculas.domain.usecase.GetMoreMoviesPopular
import ar.edu.unlam.apppeliculas.domain.usecase.GetMovies
import ar.edu.unlam.apppeliculas.domain.usecase.GetMoviesTopRated
import ar.edu.unlam.apppeliculas.domain.usecase.GetMoviesTrending
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

    class MovieViewModel() : ViewModel() {
        private  var _isLoading = MutableLiveData<Boolean>()
         var seMandoLaLlamada = MutableLiveData<Boolean>()

        private var getMoviesUseCase = GetMovies()
        private var getMoreMoviesPopular = GetMoreMoviesPopular()
        private var getMoviesTrending = GetMoviesTrending()
        private var getMoviesTopRated = GetMoviesTopRated()

        var moviesPopular = MutableLiveData<List<MovieModel>?>()

        val isLoading : LiveData<Boolean> get() = _isLoading
        val lastVisible = MutableStateFlow(0)


        fun onCreate() {
            viewModelScope.launch {
                _isLoading.postValue(true)
                val result = getMoviesUseCase()
                moviesPopular.postValue(result)

                val result2 = getMoviesTrending()
                val result3 = getMoviesTopRated()
                if (!result.isNullOrEmpty() && !result2.isNullOrEmpty() && !result3.isNullOrEmpty()) {
                    _isLoading.postValue(false)
                }
            }
        }

        init {
            var page = 2
            viewModelScope.launch {
                lastVisible.collect{
                    notifyLastVisible(it,page)
                    page++
           
                }

            }

        }

        suspend fun notifyLastVisible(position: Int, page: Int) {
            viewModelScope.launch {
                seMandoLaLlamada.postValue(false)
              var result= getMoreMoviesPopular.invoke(position, page)
              if (!result.isNullOrEmpty()){
                  seMandoLaLlamada.postValue(true)
              }

            }
        }
    }

