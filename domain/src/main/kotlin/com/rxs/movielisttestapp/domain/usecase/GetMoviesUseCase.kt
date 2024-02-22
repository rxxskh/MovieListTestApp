package com.rxs.movielisttestapp.domain.usecase

import com.rxs.movielisttestapp.domain.common.Resource
import com.rxs.movielisttestapp.domain.model.Movie
import com.rxs.movielisttestapp.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    operator fun invoke(): Flow<Resource<List<Movie>>> = flow {
        try {
            val movies =
                movieRepository.getMovies()
            emit(Resource.Success<List<Movie>>(data = movies))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<Movie>>(
                    message = e.localizedMessage ?: "Ошибка при выполнении HTTP запроса"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<List<Movie>>(message = "Ошибка подключения сети"))
        }
    }.flowOn(Dispatchers.IO)
}