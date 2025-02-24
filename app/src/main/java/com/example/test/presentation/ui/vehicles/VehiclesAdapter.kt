package com.example.test.presentation.ui.vehicles

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.databinding.VehiclesItemBinding
import com.example.test.domain.models.BrandsItem
import com.example.test.domain.models.VehicleItem
import com.example.test.presentation.utils.collapseTextView
import com.example.test.presentation.utils.expandTextView
import com.example.test.presentation.utils.loadGlideImage

class VehiclesAdapter(
    val listener: (Int) -> Unit
) : RecyclerView.Adapter<VehiclesAdapter.VehicleViewHolder>() {

    private var vehiclesList: List<VehicleItem> = emptyList()
    private var compareCount=0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val binding = VehiclesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehicleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val vehicleItem = vehiclesList[position]
        holder.bind(vehicleItem,position)

    }

    override fun getItemCount(): Int = vehiclesList.size

    inner class VehicleViewHolder(private val binding: VehiclesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(vehicleItem: VehicleItem,position: Int) {

            with(binding) {
                setDateInBinding(vehicleItem)
                displayCard(position)
                favouriteIconClickLisener(vehicleItem)
                compareIconClickLisener(vehicleItem)
                differenceTextClickLicener(vehicleItem)
            }


        }

         private fun VehiclesItemBinding.differenceTextClickLicener(vehicleItem: VehicleItem) {
             differenceLinear.setOnClickListener {
                 if (vehicleItem.vehicleUi.isTextExpand)
                     collapseTextView(textList) {
                         binding.selectIcon.setImageResource(R.drawable.difference_not_selected_icon)
                         binding.differenceText.setTextColor(
                             ContextCompat.getColor(
                                 binding.root.context,
                                 R.color.differenceTextColor
                             )
                         )
                         binding.downIcon.setImageResource(R.drawable.difference_down_icon)
                     }
                 else
                     expandTextView(textList) {
                         binding.selectIcon.setImageResource(R.drawable.difference_selected_icon)
                         binding.differenceText.setTextColor(
                             ContextCompat.getColor(
                                 binding.root.context,
                                 R.color.colorSecondary
                             )
                         )
                         binding.downIcon.setImageResource(R.drawable.difference_top_icon)
                     }

                 vehicleItem.vehicleUi.isTextExpand = !vehicleItem.vehicleUi.isTextExpand

             }
         }

         private fun compareIconClickLisener(vehicleItem: VehicleItem) {
             binding.compareIcon.setOnClickListener {
                 vehicleItem.vehicleUi.isCompareIcon = !vehicleItem.vehicleUi.isCompareIcon
                 changeDrawableColor(vehicleItem.vehicleUi.isCompareIcon, binding.compareIcon)
                 if (vehicleItem.vehicleUi.isCompareIcon)
                     compareCount++
                 else
                     compareCount--
                 listener(compareCount)
             }
         }

         private fun favouriteIconClickLisener(vehicleItem: VehicleItem) {
             binding.favouriteIcon.setOnClickListener {
                 vehicleItem.vehicleUi.isFavouriteIcon = !vehicleItem.vehicleUi.isFavouriteIcon
                 changeDrawableColor(vehicleItem.vehicleUi.isFavouriteIcon, binding.favouriteIcon)
             }
         }

         private fun changeDrawableColor(isColored: Boolean,v:View) {
            val drawable = v.background
            if (isColored)
                drawable.setTint(ContextCompat.getColor(v.context, R.color.colorSecondary))
            else
                drawable.setTint(ContextCompat.getColor(v.context, R.color.carIconColor))
            v.background=drawable
        }

        private fun VehiclesItemBinding.displayCard(position: Int) {
            if (position == 0) {
                firstCard.visibility = View.VISIBLE
                secondCard.visibility = View.VISIBLE
                thirdCard.visibility = View.GONE
            } else {
                firstCard.visibility = View.GONE
                secondCard.visibility = View.VISIBLE
                thirdCard.visibility = View.VISIBLE
            }
        }

        private fun VehiclesItemBinding.setDateInBinding(vehicleItem: VehicleItem) {
            loadGlideImage(
                vehicleItem.model_image,
                carImage,
                6
            )
            carName.text = vehicleItem.name
            carYear.text = vehicleItem.year
            carModel.text = vehicleItem.model
            carPrice.text = vehicleItem.price.toString()
            differenceText.text = "${vehicleItem.extra_attributes.size} ${binding.root.resources.getString(R.string.differences)}"
            textList.text = vehicleItem.extra_attributes.joinToString("")
            changeDrawableColor(vehicleItem.vehicleUi.isFavouriteIcon, binding.favouriteIcon)
            changeDrawableColor(vehicleItem.vehicleUi.isCompareIcon, binding.compareIcon)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<VehicleItem>) {
        vehiclesList=newList
        notifyDataSetChanged()
    }




}
