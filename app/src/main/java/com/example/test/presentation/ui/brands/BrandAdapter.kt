package com.example.test.presentation.ui.brands

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.BrandItemBinding
import com.example.test.domain.models.BrandsItem
import com.example.test.presentation.utils.loadImageWithShimmer

class BrandAdapter(
    val listener: (BrandsItem) -> Unit
) :
    RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {
    private var brandList: List<BrandsItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val binding = BrandItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrandViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val brand = brandList[position]
        holder.itemView.setOnClickListener{
            listener(brand)
        }
        holder.bind(brand)
    }

    override fun getItemCount(): Int = brandList.size

    inner class BrandViewHolder(private val binding: BrandItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(brand: BrandsItem) {

            loadImageWithShimmer(
                imageUri=brand.image,
                image=binding.image,
                shimmer=binding.shimmer
            )
            binding.appCompatTextView.text = brand.name
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<BrandsItem>) {
        brandList=newList
        notifyDataSetChanged()
    }



}
