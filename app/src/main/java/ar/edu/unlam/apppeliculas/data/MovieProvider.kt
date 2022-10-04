package ar.edu.unlam.apppeliculas.data

import ar.edu.unlam.apppeliculas.domain.model.MovieModel

class MovieProvider {
    companion object{

        var movies:List<MovieModel> = emptyList()
        var moviesTrending:List<MovieModel> = emptyList()
        var moviesTopRated:List<MovieModel> = emptyList()
    }
}