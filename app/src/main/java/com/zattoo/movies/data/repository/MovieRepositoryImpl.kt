package com.zattoo.movies.data.repository

import com.zattoo.movies.common.Resource
import com.zattoo.movies.data.remote.MovieApi
import com.zattoo.movies.data.remote.dto.MovieListDto
import com.zattoo.movies.data.remote.dto.MovieListOffersDto
import com.zattoo.movies.domain.entities.Currency
import com.zattoo.movies.domain.entities.Image
import com.zattoo.movies.domain.entities.Movie
import com.zattoo.movies.domain.entities.Price
import com.zattoo.movies.domain.repository.MovieRepository
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
                emit(Resource.Error(message = "Couldn't load data"))
                emit(Resource.Loading(false))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                emit(Resource.Loading(false))
            }
        }
    }

    private fun createMovies(
        movieDetails: MovieListDto,
        movieListOffersDto: MovieListOffersDto
    ): List<Movie> {
        return movieListOffersDto.movieOffers.mapNotNull { offers ->
            val details = movieDetails.movieData.find { it.movieId == offers.movieId }
            details?.let {
                val movieOfferPrice = offers.price
                val currency = Currency(movieOfferPrice.last().toString())
                val price = movieOfferPrice.substring(0 until movieOfferPrice.length - 1).toFloat()
                Movie(
                    id = it.movieId,
                    title = it.title,
                    subtitle = it.subTitle,
                    price = Price(price, currency),
                    image = Image(movieListOffersDto.imageBase + offers.image),
                    availability = offers.available
                )
            }
        }
    }

}