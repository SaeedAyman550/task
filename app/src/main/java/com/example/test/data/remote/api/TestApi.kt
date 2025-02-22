package com.example.test.data.remote.api


import com.example.test.data.remote.dto.BrandsDto
import com.example.test.data.remote.dto.ModelsDto
import com.example.test.data.remote.dto.VehiclesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TestApi {

    @GET("brands")
    suspend fun getBrands(
        @Query("category") category: Int,
    ): BrandsDto

    @GET("models")
    suspend fun getModels(
        @Query("page") page: Int,
        @Query("brand")brand: Int,
        @Query("category") category: Int,
        ): ModelsDto

    @GET("vehicles")
    suspend fun getVehicles(
        @Query("model") model: Int,
        @Query("brand")brand: Int,
        @Query("category") category: Int,
    ): VehiclesDto
}