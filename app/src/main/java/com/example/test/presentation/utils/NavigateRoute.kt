package com.example.test.presentation.utils

import androidx.navigation.NavOptions
import com.example.test.R

private const val ModelsScreen = "test://example.example.test/models/"
private const val VehiclesScreen = "test://example.example.test/vehicles/"
const val brandIdNavigate="brandId"
const val modelIdNavigate="modelId"

fun navigateToModelScreenUri(brandId:Int,imageUri:String) =
    "${ModelsScreen}${brandId}/${imageUri}"

fun navigateToVehiclesScreenUri(brandId:String,modelId:Int,imageUri:String) =
    "${VehiclesScreen}${brandId}/${modelId}/${imageUri}"


fun getNavOptionAnimation() =
    NavOptions.Builder()
        .setEnterAnim(R.anim.from_right_navigate)
        .setPopEnterAnim(R.anim.from_left_navigate)
        .setExitAnim(R.anim.to_left_navigate)
        .setPopExitAnim(R.anim.to_right_navigate)
        .build()