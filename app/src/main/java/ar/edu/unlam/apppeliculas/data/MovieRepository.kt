package ar.edu.unlam.apppeliculas.data

import ar.edu.unlam.apppeliculas.domain.model.MovieModel
import ar.edu.unlam.apppeliculas.data.network.MovieService
import java.text.FieldPosition

class MovieRepository {
    private val api = MovieService()
    private var paginaPopular = 2
    private var paginaTrending = 2
    private var paginaTopRated = 2

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

    suspend fun getMoreMoviesPopular(position:Int): List<MovieModel> {
        return if (position==MovieProvider.movies.size-1){
            val response:List<MovieModel> = api.getMoreMoviesPopular(paginaPopular)
            MovieProvider.movies += response
            paginaPopular++
            response
        }else{
            emptyList()
        }
    }

    suspend fun getMoreMoviesTrending(position:Int): List<MovieModel> {
        return if (position==MovieProvider.moviesTrending.size-1){
            val response:List<MovieModel> = api.getMoreMoviesTrending(paginaTrending)
            MovieProvider.moviesTrending += response
            paginaTrending++
            response
        }else{
            emptyList()
        }
    }

    suspend fun getMoreMoviesTopRated(position:Int): List<MovieModel> {
        return if (position==MovieProvider.moviesTopRated.size-1){
            val response:List<MovieModel> = api.getMoreMoviesTopRated(paginaTopRated)
            MovieProvider.moviesTopRated += response
            paginaTopRated++
            response
        }else{
            emptyList()
        }
    }

}