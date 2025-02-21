package com.example.test.data.repository



import com.example.test.data.mapper.FromDtoToBrandsModelMapper
import com.example.test.data.remote.api.TestApi
import com.example.test.domain.models.BrandsModel
import com.example.test.domain.repository.TestRepo
import com.example.test.domain.utils.Resourse

class TestRepoImp(
    private val api: TestApi
): TestRepo  {


    override suspend fun getBrands(category: Int): Resourse<BrandsModel> {
    return try {
        val result = api.getBrands(category)

        if (result.message.isNullOrEmpty())
            Resourse.Success(FromDtoToBrandsModelMapper.map(result))
        else
            Resourse.Error(result.message.toString() )
    } catch (e: Exception) {
        Resourse.Error("${e.message.toString()} exception")
    }

    }



}