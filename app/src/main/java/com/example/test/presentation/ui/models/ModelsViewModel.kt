package com.example.test.presentation.ui.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.use_cases.ModelsUseCase
import com.example.test.domain.utils.Resourse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class ModelsViewModel @Inject constructor(
    private val useCase: ModelsUseCase
) : ViewModel() {



    private val _modelStateFlow = MutableStateFlow(ModelsState())
    val modelStateFlow = _modelStateFlow.asStateFlow()


    fun getModels(
        brandId:Int
    ) =
        useCase(
                brandId
        ).onEach {
            when (it) {
                is Resourse.Loading ->_modelStateFlow.emit(ModelsState(loading = true))
                is Resourse.Success ->{
                        it.data?.flow?.collect {pagingData ->
                        _modelStateFlow.emit(ModelsState(data = pagingData))
                    }
                }
                is Resourse.Error ->ModelsState(error = it.message)
            }

        }.launchIn(viewModelScope)




    }