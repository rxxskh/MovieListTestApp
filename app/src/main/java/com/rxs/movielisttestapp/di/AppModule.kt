package com.rxs.movielisttestapp.di

import com.rxs.movielisttestapp.data.MovieApi
import com.rxs.movielisttestapp.data.repository.MovieRepositoryImpl
import com.rxs.movielisttestapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi(): com.rxs.movielisttestapp.data.MovieApi =
        Retrofit.Builder().baseUrl("https://s3-eu-west-1.amazonaws.com/sequeniatesttask/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(com.rxs.movielisttestapp.data.MovieApi::class.java)

    @Provides
    @Singleton
    fun provideMovieRepository(movieApi: com.rxs.movielisttestapp.data.MovieApi): com.rxs.movielisttestapp.domain.repository.MovieRepository =
        com.rxs.movielisttestapp.data.repository.MovieRepositoryImpl(movieApi = movieApi)
}