package ar.edu.unlam.apppeliculas.data.network

import ar.edu.unlam.apppeliculas.domain.model.ResultMovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiClient {

    @GET("movie/popular?api_key=213e4816a874caa847bacd93220509ce&language=es-ES")
    suspend fun getAllMovies(@Query("page")page:Int): Response<ResultMovieModel>

    @GET("movie/top_rated?api_key=213e4816a874caa847bacd93220509ce&language=es-ES")
    suspend fun getAllMoviesTopRated(@Query("page")page: Int): Response<ResultMovieModel>

    @GET("trending/all/week?api_key=213e4816a874caa847bacd93220509ce&language=es-ES")
    suspend fun getMoviesTrending(@Query("page")page: Int): Response<ResultMovieModel>



}