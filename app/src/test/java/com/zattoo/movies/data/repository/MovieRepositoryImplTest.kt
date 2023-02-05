package com.zattoo.movies.data.repository

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.zattoo.movies.common.Resource
import com.zattoo.movies.data.remote.MovieApi
import com.zattoo.movies.data.remote.dto.MovieListDto
import com.zattoo.movies.data.remote.dto.MovieListOffersDto
import com.zattoo.movies.domain.entities.Currency
import com.zattoo.movies.domain.entities.Image
import com.zattoo.movies.domain.entities.Movie
import com.zattoo.movies.domain.entities.Price
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.io.IOException


class MovieRepositoryImplTest {

    private lateinit var mockMovieApi: MovieApi
    private lateinit var repository: MovieRepositoryImpl
    private lateinit var successResponseMovieOffersDto: MovieListOffersDto
    private lateinit var successResponseMovieListDto: MovieListDto
    private lateinit var successResponseMovieList: List<Movie>

    @Before
    fun setUp() {
        mockMovieApi = mock()
        repository = MovieRepositoryImpl(mockMovieApi)
        successResponseMovieOffersDto = MovieListOffersDto(
            imageBase = "https://dummyimage.com",
            listOf(
                MovieListOffersDto.MovieOffer(
                    available = false,
                    image = "/600x400/ffffff/ffffff",
                    movieId = 23,
                    price = "32.00€"
                )
            )
        )
        successResponseMovieListDto = MovieListDto(
            movieData = listOf(
                MovieListDto.MovieData(movieId = 23, subTitle = "subtitle", title = "title")
            )
        )

        successResponseMovieList = listOf(Movie(
            subtitle = "subtitle", title = "title",
            availability = false, price = Price(value = 32.00f, currency = Currency(symbol = "€")),
            image = Image(url = "https://dummyimage.com/600x400/ffffff/ffffff"),
            id = 23
        ))
    }

    @Test
    fun `is Loading is true on start`() = runBlocking {
        Mockito.`when`(mockMovieApi.fetchMovieList()).thenReturn(null)
        val firstItem = repository.getMovies().first()
        assertThat((firstItem as Resource.Loading).isLoading).isEqualTo(true)
    }

    @Test
    fun `is Loading is false on end`() = runBlocking {
        Mockito.`when`(mockMovieApi.fetchMovieList()).thenReturn(successResponseMovieListDto)
        Mockito.`when`(mockMovieApi.fetchMovieListOffers())
            .thenReturn(successResponseMovieOffersDto)
        val secondItem = repository
            .getMovies().drop(2).first()
        assertThat((secondItem as Resource.Loading).isLoading).isEqualTo(false)
    }

    @Test
    fun `is status is instance of error`() = runBlocking {
        Mockito.`when`(mockMovieApi.fetchMovieList()).thenAnswer {
            throw  IOException()
        }
        val secondItem = repository
            .getMovies().drop(2).first()
        assertThat((secondItem as Resource.Loading).isLoading).isEqualTo(false)
    }

    @Test
    fun `if resource type is an instance of success and returns proper list of movie type`() =
        runBlocking {
            Mockito.`when`(mockMovieApi.fetchMovieList()).thenReturn(successResponseMovieListDto)
            Mockito.`when`(mockMovieApi.fetchMovieListOffers())
                .thenReturn(successResponseMovieOffersDto)
            val secondItem = repository
                .getMovies().drop(1).first()
            assertThat((secondItem as Resource.Success).data).isEqualTo(
                successResponseMovieList
            )
        }
}