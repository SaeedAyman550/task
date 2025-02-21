package com.example.test.presentation.ui.brands

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.models.BrandsData
import com.example.test.domain.use_cases.BrandsUseCase
import com.example.test.domain.utils.ErrorData
import com.example.test.domain.utils.Resourse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class BrandViewModel @Inject constructor(
    private val useCase: BrandsUseCase
) : ViewModel() {


    private val _brandStateFlow = MutableStateFlow(BrandsState())
    val brandStateFlow = _brandStateFlow.asStateFlow()

    var brandList= mutableListOf<BrandsData>()

    fun getBrands(

    ) =
        useCase(

        ).onEach {
            when (it) {
                is Resourse.Loading -> _brandStateFlow.emit(
                    BrandsState(loading = true)
                )
                is Resourse.Success -> _brandStateFlow.emit(
                    BrandsState(data = it.data?.data ?: emptyList())
                )
                is Resourse.Error -> {
                    when(it.errorType){
                        ErrorData.NotFoundError ->
                            _brandStateFlow.emit(BrandsState(notFoundError = true))

                        ErrorData.UnauthorizedError ->
                            _brandStateFlow.emit(BrandsState(unauthorizedError = true))

                        ErrorData.UnKnownError ->
                            _brandStateFlow.emit(BrandsState(unKnownError = it.message))
                        null -> {}
                    }

                }
            }

        }.launchIn(viewModelScope)

}