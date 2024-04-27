package com.example.workdaychallenge.di

import android.content.Context
import com.example.workdaychallenge.data.repository.PokemonRepository
import com.example.workdaychallenge.data.repository.PokemonRepositoryImpl
import com.example.workdaychallenge.network.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private  const val BASE_URL = "https://pokeapi.co/api/v2/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun provideRetrofit() : PokemonService {
        val instance = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return instance.create(PokemonService::class.java)
    }


    @Provides
    fun providePokemonRepository(service: PokemonService): PokemonRepository {
        return PokemonRepositoryImpl(service)
    }
}
