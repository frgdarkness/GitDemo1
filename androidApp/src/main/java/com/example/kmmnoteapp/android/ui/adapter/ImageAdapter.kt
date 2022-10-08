package com.example.kmmnoteapp.android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kmmnoteapp.android.databinding.ImageItemBinding
import com.example.kmmnoteapp.datasource.data.MyImage

class ImageAdapter(val onclick: (position: Int) -> Unit): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private val data = mutableListOf<MyImage>()

    class ImageViewHolder(val binding: ImageItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = data[position]
        holder.binding.also {
            it.tvInfo.text = image.author
            Glide.with(holder.binding.root)
                .load(image.downloadUrl)
                .override(500)
                .into(it.imageView)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun loadData(images: List<MyImage>) {
        data.clear()
        data.addAll(images)
        notifyDataSetChanged()
    }
}