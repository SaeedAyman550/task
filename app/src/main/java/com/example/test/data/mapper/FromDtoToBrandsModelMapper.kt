package com.example.test.data.mapper



import com.example.data.mapper.Mapper
import com.example.test.data.remote.dto.BrandsDto
import com.example.test.domain.models.BrandsItem
import com.example.test.domain.models.BrandsModel


object FromDtoToBrandsModelMapper : Mapper<BrandsDto, BrandsModel> {
    override fun map(input: BrandsDto?): BrandsModel =
        if (input == null)
            BrandsModel()
        else
            BrandsModel(
                data = input.data?.map { dataDto ->
                    BrandsItem(
                        id = dataDto.id?:-1,
                        image = dataDto.image?:"",
                        name = dataDto.name?:""
                    )
                }?: emptyList(),
                message = input.message?:""

            )



}



