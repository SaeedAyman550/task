package com.example.test.domain.models



data class ModelsModel(

    val data: List<ModelsItem> = emptyList(),
    val message: String=""
)

data class ModelsItem(

    val image: String="",
    val attribute_value: String="",
    val identification_attribute_id: Int= -1,
    val identification_attribute_value_id: Int= -1,
    val name: String="",
    val parent_brand: String="",
    val parent_brand_image: String="",
    val price: Int=-1
)
