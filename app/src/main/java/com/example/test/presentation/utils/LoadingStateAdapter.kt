package com.example.test.presentation.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.LoadStateItemBinding

class LoadingStateAdapter: LoadStateAdapter<LoadingStateAdapter.NetworkStateItemViewHolder>() {


    class NetworkStateItemViewHolder(
        private val binding: LoadStateItemBinding,

    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            when(loadState){
                is LoadState.Error -> binding.progressBar.visibility=View.GONE
                LoadState.Loading -> binding.progressBar.visibility=View.VISIBLE
                is LoadState.NotLoading -> binding.progressBar.visibility=View.GONE
            }
        }
    }

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemViewHolder {
        val holder=NetworkStateItemViewHolder(binding = LoadStateItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        val layoutParams = GridLayoutManager.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        holder.itemView.layoutParams=layoutParams
        return holder
    }
}