package ar.edu.unlam.apppeliculas.data.network

import ar.edu.unlam.apppeliculas.core.RetrofitHelper
import ar.edu.unlam.apppeliculas.domain.model.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getMovies():List<MovieModel>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(MovieApiClient::class.java).getAllMovies(1)
            response.body()?.results ?: emptyList()
        }

    }
    suspend fun getMoviesTrending():List<MovieModel>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(MovieApiClient::class.java).getMoviesTrending(1)
            response.body()?.results ?: emptyList()
        }

    }

    suspend fun getMoviesTopRated():List<MovieModel>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(MovieApiClient::class.java).getAllMoviesTopRated(1)
            response.body()?.results ?: emptyList()
        }

    }

    suspend fun getMoreMoviesPopular(page:Int): List<MovieModel> {
        return withContext(Dispatchers.IO){
            val response = retrofit.create(MovieApiClient::class.java).getAllMovies(page)
            response.body()?.results ?: emptyList()
        }
    }
    suspend fun getMoreMoviesTrending(page:Int): List<MovieModel> {
        return withContext(Dispatchers.IO){
            val response = retrofit.create(MovieApiClient::class.java).getMoviesTrending(page)
            response.body()?.results ?: emptyList()
        }
    }
    suspend fun getMoreMoviesTopRated(page:Int): List<MovieModel> {
        return withContext(Dispatchers.IO){
            val response = retrofit.create(MovieApiClient::class.java).getAllMoviesTopRated(page)
            response.body()?.results ?: emptyList()
        }
    }
}
