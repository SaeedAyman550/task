package com.example.test.domain.models

data class VehiclesModel(
    val data: List<VehicleItem> = emptyList(),
    val message:String=""

)

data class VehicleItem(
    val brand_image: String="",
    val extra_attributes: List<String> = emptyList(),
    val model: String="",
    val model_image: String="",
    val name: String="",
    val price: Int=0,
    val year: String="",
    var vehicleUi:VehicleUi=VehicleUi()
)

data class VehicleUi(
    var isCompareIcon:Boolean=false,
    var isFavouriteIcon:Boolean=false,
    var isTextExpand:Boolean=false,

    )