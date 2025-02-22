package com.example.test.domain.repository



import com.example.test.domain.models.BrandsModel
import com.example.test.domain.models.ModelsModel
import com.example.test.domain.models.VehiclesModel
import com.example.test.domain.utils.Resourse


interface TestRepo {

    suspend fun getBrands(
         category: Int=3
    ): Resourse<BrandsModel>

    suspend fun getModels(
        page: Int,
        brand: Int,
        category: Int=3,
    ): Resourse<ModelsModel>

    suspend fun getVehicles(
        model: Int,
        brand: Int,
        category: Int=3,
    ): Resourse<VehiclesModel>



}