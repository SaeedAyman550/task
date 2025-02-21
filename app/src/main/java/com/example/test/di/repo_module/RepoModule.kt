package com.example.test.di.repo_module


import com.example.test.data.remote.api.TestApi
import com.example.test.data.repository.TestRepoImp
import com.example.test.domain.repository.TestRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideTestRepo(api:TestApi): TestRepo {
        return TestRepoImp(api)
    }




}