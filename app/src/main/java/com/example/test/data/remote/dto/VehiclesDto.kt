package com.example.test.data.remote.dto

data class VehiclesDto(
    val data: List<Data>?,
    val message:String?

)

data class Data(
    val additional_images: List<String>?,
    val brand: String?,
    val brand_id: Int?,
    val brand_image: String?,//
    val extra_attributes: List<String>?,//
    val generation: String?,
    val has_seller: Boolean?,
    val hidden_price: Int?,
    val id: Int?,
    val in_compare_list: Boolean?,
    val is_wishlisted: Boolean?,
    val model: String?,//
    val model_id: Int?,
    val model_image: String?,//
    val name: String?,//
    val price: Int?,//
    val selling_type: Any?,
    val stock_available: Boolean?,
    val year: String?//
)