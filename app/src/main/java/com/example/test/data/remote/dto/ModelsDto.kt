package com.example.test.data.remote.dto



data class ModelsDto(
    val ads: List<Ad>?,
    val data: List<ModelsDataDto>?,
    val message: String?
)

data class ModelsDataDto(
    val attribute: String?,
    val attribute_image: String?,
    val attribute_value: String?,
    val attributes_hint_list: List<AttributesHint>?,
    val hidden_price: Int?,
    val id: Int?,
    val identification_attribute_id: Int?,
    val identification_attribute_value_id: Int?,
    val image: String?,
    val least_deposit: Double?,
    val least_installment: Int?,
    val name: String?,
    val parent_brand: String?,
    val parent_brand_image: String?,
    val price: Int?,
    val vehicle_id: Int?
)
data class AttributesHint(
    val attribute: String?,
    val image: String?,
    val value: String?
)

data class Ad(
    val id: Int?,
    val image: String?,
    val position: Int?,
    val url: String?
)