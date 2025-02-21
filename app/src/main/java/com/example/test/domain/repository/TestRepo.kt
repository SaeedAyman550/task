package com.example.test.domain.repository



import com.example.test.domain.models.BrandsModel
import com.example.test.domain.utils.Resourse


interface TestRepo {

    suspend fun getBrands(
         category: Int=3
    ): Resourse<BrandsModel>



}