package com.example.test.data.mapper



import com.example.data.mapper.Mapper
import com.example.test.data.remote.dto.ModelsDto
import com.example.test.domain.models.ModelsItem
import com.example.test.domain.models.ModelsModel


object FromDtoToModelsModelMapper : Mapper<ModelsDto, ModelsModel> {

    override fun map(input: ModelsDto?): ModelsModel =
            if (input == null)
            ModelsModel()
        else
                ModelsModel(
                data = input.data?.map { modelDto ->
                    ModelsItem(
                        id = modelDto.id?:-1,
                         image =modelDto.image?:"",
                     attribute_value=modelDto.attribute_value?:"",
                     identification_attribute_id=modelDto.identification_attribute_id?:1,
                     identification_attribute_value_id=modelDto.identification_attribute_value_id?:-1,
                     name=modelDto.name?:"",
                     parent_brand=modelDto.parent_brand?:"",
                     parent_brand_image=modelDto.parent_brand_image?:"",
                     price=modelDto.price?:-1
                    )
                }?: emptyList(),
                message = input.message?:""

            )


}



