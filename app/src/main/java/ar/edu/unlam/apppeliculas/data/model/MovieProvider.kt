package ar.edu.unlam.apppeliculas.data.model

class MovieProvider {
    companion object{

        var movies:List<MovieModel> = emptyList()
        var moviesTrending:List<MovieModel> = emptyList()
        var moviesTopRated:List<MovieModel> = emptyList()
    }
}