package com.example.test.domain.models

data class BrandsModel(
    val data: List<BrandsData> = emptyList(),
    val message:String=""
)
data class BrandsData(
    val id: Int=-1,
    val image: String="",
    val name: String=""
)