package com.example.test.domain.use_cases


import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.test.domain.models.ModelsItem
import com.example.test.domain.models.VehiclesModel
import com.example.test.domain.paging_sourse.ModelsPagingSource
import com.example.test.domain.repository.TestRepo
import com.example.test.domain.utils.Resourse
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VehiclesUseCase @Inject constructor(private val repo: TestRepo) {

    operator fun invoke(
        model:Int,
        brand: Int
    ) = flow<Resourse<VehiclesModel>> {

            val getVehicles  = repo.getVehicles(model=model,brand=brand)
            emit(getVehicles)

    }
}

