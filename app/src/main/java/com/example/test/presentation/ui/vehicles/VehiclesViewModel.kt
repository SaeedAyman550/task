package com.example.test.presentation.ui.vehicles

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.use_cases.ModelsUseCase
import com.example.test.domain.use_cases.VehiclesUseCase
import com.example.test.domain.utils.Resourse
import com.example.test.presentation.utils.brandIdNavigate
import com.example.test.presentation.utils.modelIdNavigate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class VehiclesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: VehiclesUseCase
) : ViewModel() {

    private val _vehiclesStateFlow = MutableStateFlow(VehiclesState())
    val vehiclesStateFlow = _vehiclesStateFlow.asStateFlow()
    var nestedScrollPosition=0

    private val brandId: String = savedStateHandle[brandIdNavigate] ?: ""
    private val modelId: String = savedStateHandle[modelIdNavigate] ?: ""

    init {
        getVehicles(brandId = brandId.toInt(), modelId = modelId.toInt())
    }

    fun getVehicles(
        brandId:Int,
        modelId: Int
    ) =
        useCase(
                model= modelId,
               brand =  brandId
        ).onEach {
            when (it) {
                is Resourse.Loading ->
                    _vehiclesStateFlow.emit(VehiclesState(loading = true))
                is Resourse.Success ->
                    _vehiclesStateFlow.emit(VehiclesState(data = it.data?.data?: emptyList()))
                is Resourse.Error ->
                    _vehiclesStateFlow.emit(VehiclesState(error = it.message))
            }

        }.launchIn(viewModelScope)




    }