package com.example.test.presentation.ui.models

import androidx.paging.PagingData
import com.example.test.domain.models.ModelsItem


data class ModelsState(
    val loading:Boolean=false,
    val data:PagingData<ModelsItem> =PagingData.empty(),
    val error:String="",

    )
