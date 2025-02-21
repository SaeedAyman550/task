package com.example.test.data.remote.api


import com.example.test.data.remote.dto.BrandsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TestApi {

    @GET("brands")
    suspend fun getBrands(
        @Query("category") category: Int,
    ): BrandsDto


}