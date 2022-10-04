package ar.edu.unlam.apppeliculas.domain.usecase

import ar.edu.unlam.apppeliculas.data.MovieRepository
import ar.edu.unlam.apppeliculas.domain.model.MovieModel

class GetMoviesTopRated {
    private val repository = MovieRepository()

    suspend operator fun invoke():List<MovieModel>?{
        return repository.getMoviesTopRated()
    }
}