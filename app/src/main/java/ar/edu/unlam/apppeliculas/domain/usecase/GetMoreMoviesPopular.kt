package ar.edu.unlam.apppeliculas.domain.usecase

import ar.edu.unlam.apppeliculas.data.MovieRepository
import ar.edu.unlam.apppeliculas.domain.model.MovieModel
import java.text.FieldPosition

class GetMoreMoviesPopular {
    private val repository = MovieRepository()

    suspend operator fun invoke(position: Int, page: Int):List<MovieModel>?{

        return repository.getMoreMoviesPopular(position,page)
    }
}