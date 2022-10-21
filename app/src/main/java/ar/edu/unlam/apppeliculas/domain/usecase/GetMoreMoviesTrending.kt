package ar.edu.unlam.apppeliculas.domain.usecase

import ar.edu.unlam.apppeliculas.data.MovieRepository
import ar.edu.unlam.apppeliculas.domain.model.MovieModel

class GetMoreMoviesTrending {
    private val repository = MovieRepository()

    suspend operator fun invoke(position: Int):List<MovieModel>?{

        return repository.getMoreMoviesTrending(position)
    }
}