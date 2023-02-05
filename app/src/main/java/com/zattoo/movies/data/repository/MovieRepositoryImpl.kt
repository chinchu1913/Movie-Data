package com.zattoo.movies.data.repository

import com.zattoo.movies.data.remote.MovieApi
import com.zattoo.movies.data.remote.dto.MovieListDto
import com.zattoo.movies.data.remote.dto.MovieListOffersDto
import com.zattoo.movies.domain.entities.Currency
import com.zattoo.movies.domain.entities.Image
import com.zattoo.movies.domain.entities.Movie
import com.zattoo.movies.domain.entities.Price
import com.zattoo.movies.domain.repository.MovieRepository
import common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
) : MovieRepository {
    override suspend fun getMovies(): Flow<Resource<List<Movie>>> {
        return flow {
            emit(value = Resource.Loading(true))
            try {
                val responseMovies = api.fetchMovieList()
                val responseOffers = api.fetchMovieListOffers()
                emit(
                    Resource.Success(
                        data = createMovies(responseMovies, responseOffers)
                    )
                )
                emit(
                    Resource.Loading(
                        isLoading = false
                    )
                )
                return@flow
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            }
        }
    }

    private fun createMovies(
        movieDetails: MovieListDto,
        movieListOffersDto: MovieListOffersDto
    ): List<Movie> {
        return movieListOffersDto.offers.mapNotNull { offers ->
            val details = movieDetails.movie_data.find { it.movie_id == offers.movie_id }
            details?.let {
                val movieOfferPrice = offers.price
                val currency = Currency(movieOfferPrice.last().toString())
                val price = movieOfferPrice.substring(0 until movieOfferPrice.length - 1).toFloat()
                Movie(
                    id = it.movie_id,
                    title = it.title,
                    subtitle = it.sub_title,
                    price = Price(price, currency),
                    image = Image(movieListOffersDto.image_base + offers.image),
                    availability = offers.available
                )
            }
        }
    }

}