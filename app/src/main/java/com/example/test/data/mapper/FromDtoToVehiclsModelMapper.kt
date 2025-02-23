package com.example.test.data.mapper



import com.example.data.mapper.Mapper
import com.example.test.data.remote.dto.BrandsDto
import com.example.test.data.remote.dto.VehiclesDto
import com.example.test.domain.models.BrandsItem
import com.example.test.domain.models.BrandsModel
import com.example.test.domain.models.VehicleItem
import com.example.test.domain.models.VehiclesModel


object FromDtoToVehiclsModelMapper : Mapper<VehiclesDto, VehiclesModel> {

    override fun map(input: VehiclesDto?): VehiclesModel=
        if (input == null)
            VehiclesModel()
        else
            VehiclesModel(
                data = input.data?.map { vehiclDto->
                    VehicleItem(
                        brand_image=vehiclDto.brand_image?:"",
                     extra_attributes=vehiclDto.extra_attributes?.map {"+ $it\n" }?: emptyList(),
                    model=vehiclDto.model?:"",
                     model_image=vehiclDto.model_image?:"",
                    name=vehiclDto.name?:"",
                    price=vehiclDto.price?:0,
                    year=vehiclDto.year?:""
                    )
                }?: emptyList()

            )

}



