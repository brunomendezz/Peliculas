package ar.edu.unlam.apppeliculas.data

import ar.edu.unlam.apppeliculas.data.model.MovieModel
import ar.edu.unlam.apppeliculas.data.model.MovieProvider
import ar.edu.unlam.apppeliculas.data.network.MovieService

class MovieRepository {
    private val api = MovieService()

    suspend fun getAllMovies():List<MovieModel>{
        val response:List<MovieModel> = api.getMovies()
        MovieProvider.movies=response
        return response
    }
    suspend fun getMoviesTrending():List<MovieModel>{
        val response:List<MovieModel> = api.getMoviesTrending()
        MovieProvider.moviesTrending=response
        return response
    }
    suspend fun getMoviesTopRated():List<MovieModel>{
        val response:List<MovieModel> = api.getMoviesTopRated()
        MovieProvider.moviesTopRated=response
        return response
    }

}