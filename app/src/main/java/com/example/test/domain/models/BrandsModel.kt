package com.example.test.domain.models

data class BrandsModel(
    val data: List<BrandsItem> = emptyList(),
    val message:String=""
)

data class BrandsItem(
    val id: Int=-1,
    val image: String="",
    val name: String=""
)