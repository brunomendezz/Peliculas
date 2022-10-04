package ar.edu.unlam.apppeliculas.data

import ar.edu.unlam.apppeliculas.domain.model.MovieModel
import ar.edu.unlam.apppeliculas.data.network.MovieService
import java.text.FieldPosition

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

    suspend fun getMoreMoviesPopular(position:Int,page: Int): List<MovieModel> {
        return if (position==MovieProvider.movies.size-1 && page<=6){
            val response:List<MovieModel> = api.getMoreMoviesPopular(page)
            MovieProvider.movies += response

            response
        }else{
            emptyList()
        }
    }

}