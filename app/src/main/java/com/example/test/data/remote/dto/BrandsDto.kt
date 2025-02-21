package com.example.test.data.remote.dto

data class BrandsDto(
    val data: List<BrandsData>?,
    val message:String?
)
data class BrandsData(
    val id: Int?,
    val image: String?,
    val name: String?
)
