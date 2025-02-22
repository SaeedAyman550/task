package com.example.test.presentation.ui.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.ModelItemBinding
import com.example.test.domain.models.ModelsItem
import com.example.test.presentation.utils.loadImageWithShimmer

class ModelAdapter(
    comparator: DiffUtil.ItemCallback<ModelsItem>
): PagingDataAdapter<ModelsItem, ModelAdapter.MainViewHolder>(comparator){

    private var isGridDesign=false

  inner  class MainViewHolder(private val binding: ModelItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(model: ModelsItem) {
            loadImageWithShimmer(
                imageUri=model.image,
                image=binding.carImage,
                shimmer=binding.shimmer,
                radius = 6
            )
            binding.carName.text = model.name
            binding.carYear.text=model.attribute_value
            binding.carPrice.text=model.price.toString()
            if (isGridDesign)
                binding.linear.orientation = LinearLayout.VERTICAL
            else
                binding.linear.orientation = LinearLayout.HORIZONTAL
        }
    }

    fun changeScreen(isGrid:Boolean) { isGridDesign = isGrid }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ModelItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model?:ModelsItem())
    }


}