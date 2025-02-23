package com.example.test.presentation.ui.vehicles

import androidx.paging.PagingData
import com.example.test.domain.models.BrandsItem
import com.example.test.domain.models.ModelsItem
import com.example.test.domain.models.VehicleItem


data class VehiclesState(
    val loading:Boolean=false,
    val data:List<VehicleItem> = emptyList(),
    val error:String="",

    )
