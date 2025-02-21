package com.example.test.di.api_module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {


    @Provides
    @Singleton
    fun provideRetrofitPrayerTimes(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://emotive-api.creteagency.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



}