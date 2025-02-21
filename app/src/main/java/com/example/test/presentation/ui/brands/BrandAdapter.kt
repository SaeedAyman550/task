package com.example.test.presentation.ui.brands

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.test.databinding.BrandItemBinding
import com.example.test.domain.models.BrandsData
import javax.sql.DataSource

class BrandAdapter() :
    RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {
    private var brandList: List<BrandsData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val binding = BrandItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrandViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val brand = brandList[position]
        holder.bind(brand)
    }

    override fun getItemCount(): Int = brandList.size

    inner class BrandViewHolder(private val binding: BrandItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(brand: BrandsData) {
            Glide.with(itemView.context)
                .load(brand.image)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: com.bumptech.glide.load.DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.shimmer.visibility = View.GONE
                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .into(binding.image)
            binding.appCompatTextView.text = brand.name
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<BrandsData>) {
        brandList=newList
        notifyDataSetChanged()
    }



}
