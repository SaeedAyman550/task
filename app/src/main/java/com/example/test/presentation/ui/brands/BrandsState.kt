package com.example.test.presentation.ui.brands

import com.example.test.domain.models.BrandsItem


data class BrandsState(
    val loading:Boolean=false,
    val data:List<BrandsItem> = emptyList(),
    val unKnownError:String="",
    val notFoundError:Boolean=false,
    val unauthorizedError:Boolean=false
    )
