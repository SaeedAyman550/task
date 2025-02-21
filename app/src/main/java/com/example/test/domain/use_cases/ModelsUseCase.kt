package com.example.test.domain.use_cases


import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.test.domain.models.BrandsModel
import com.example.test.domain.models.ModelsData
import com.example.test.domain.models.ModelsModel
import com.example.test.domain.paging_sourse.ModelsPagingSource
import com.example.test.domain.repository.TestRepo
import com.example.test.domain.utils.Resourse
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ModelsUseCase @Inject constructor(private val repo: TestRepo) {

    operator fun invoke(
        brand: Int,
    ) = flow<Resourse<Pager<Int, ModelsData>>> {


        try {
            val getModels  = Pager(
                config = PagingConfig(
                    pageSize = 10,
                ),
                pagingSourceFactory = {
                    ModelsPagingSource(repo,brand)
                }
            )
            emit(Resourse.Success(getModels))

        } catch (e: Exception) {
            emit(Resourse.Error("${e.message}  exception"))
        }
    }
}

