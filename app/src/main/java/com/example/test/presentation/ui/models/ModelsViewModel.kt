package com.example.test.presentation.ui.models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.test.domain.use_cases.ModelsUseCase
import com.example.test.domain.utils.Resourse
import com.example.test.presentation.utils.brandIdNavigate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ModelsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: ModelsUseCase
) : ViewModel() {


    private val brandId: String = savedStateHandle[brandIdNavigate] ?: ""
    private val _modelStateFlow = MutableStateFlow(ModelsState())
    val modelStateFlow = _modelStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            getModels(brandId = brandId.toInt())
        }

    }

    fun getModels(
        brandId:Int
    ) =
        useCase(
                brandId
        ).onEach {
            when (it) {
                is Resourse.Loading ->_modelStateFlow.emit(ModelsState(loading = true))
                is Resourse.Success ->{
                        it.data?.flow?.cachedIn(viewModelScope)?.collectLatest {pagingData ->
                        _modelStateFlow.emit(ModelsState(data = pagingData))
                    }
                }
                is Resourse.Error ->_modelStateFlow.emit(ModelsState(error = it.message))

            }

        }.launchIn(viewModelScope)




    }