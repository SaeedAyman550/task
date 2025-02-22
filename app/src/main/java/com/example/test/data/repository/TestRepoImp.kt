package com.example.test.data.repository


import com.example.test.data.mapper.FromDtoToBrandsModelMapper
import com.example.test.data.mapper.FromDtoToModelsModelMapper
import com.example.test.data.mapper.FromDtoToVehiclsModelMapper
import com.example.test.data.remote.api.TestApi
import com.example.test.domain.models.BrandsModel
import com.example.test.domain.models.ModelsModel
import com.example.test.domain.models.VehiclesModel
import com.example.test.domain.repository.TestRepo
import com.example.test.domain.utils.ErrorData
import com.example.test.domain.utils.Resourse
import retrofit2.HttpException

class TestRepoImp(
    private val api: TestApi
) : TestRepo {


    override suspend fun getBrands(category: Int): Resourse<BrandsModel> {
        return try {
            val result = api.getBrands(category)

            if (result.message.isNullOrEmpty())
                Resourse.Success(FromDtoToBrandsModelMapper.map(result))
            else
                Resourse.Error(result.message.toString(), ErrorData.UnKnownError)
        } catch (e: HttpException) {
            return when (e.code()) {
                404 ->
                    Resourse.Error(errorType = ErrorData.NotFoundError)

                401 ->
                    Resourse.Error(errorType = ErrorData.UnauthorizedError)

                else ->
                    Resourse.Error(
                        "${e.message.toString()} exception",
                        ErrorData.UnKnownError
                    )
            }
        } catch (e: Exception) {
            Resourse.Error("${e.message.toString()} exception", ErrorData.UnKnownError)
        }

    }

    override suspend fun getModels(page: Int, brand: Int, category: Int): Resourse<ModelsModel> {
        return try {
            val result = api.getModels(page=page,brand=brand,category=category)
                Resourse.Success(FromDtoToModelsModelMapper.map(result))

        } catch (e: Exception) {
            Resourse.Error("${e.message.toString()} exception")
        }

    }

    override suspend fun getVehicles(
        model: Int,
        brand: Int,
        category: Int
    ): Resourse<VehiclesModel> {
        return try {
            val result = api.getVehicles(model=model,brand=brand,category=category)
            Resourse.Success(FromDtoToVehiclsModelMapper.map(result))
        } catch (e: Exception) {
            Resourse.Error("${e.message.toString()} exception")
        }
    }


}