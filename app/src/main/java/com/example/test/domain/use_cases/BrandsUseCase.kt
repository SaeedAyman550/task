package com.example.test.domain.use_cases


import com.example.test.domain.models.BrandsModel
import com.example.test.domain.repository.TestRepo
import com.example.test.domain.utils.Resourse
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BrandsUseCase @Inject constructor(private val repo: TestRepo) {

    operator fun invoke(category: Int=3) = flow<Resourse<BrandsModel>> {

            emit(Resourse.Loading())
        try {
            emit(repo.getBrands(category))
        } catch (e: Exception) {
            emit(Resourse.Error("${e.message}  exception"))
        }
    }
}