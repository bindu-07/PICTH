package com.photo.picth.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.photo.picth.R
import com.photo.picth.ui.presentation.homepage.Item
import com.photo.picth.utils.ui.Constants

class RvHorizontalBannerAdapter(private val items: List<Item>) : RecyclerView.Adapter<RvHorizontalBannerAdapter.CircleViewHolder>() {

    class CircleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivItemImage: ImageView = itemView.findViewById(R.id.imageViewSpecial)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv4_item, parent, false)
        return CircleViewHolder(view)
    }

    override fun onBindViewHolder(holder: CircleViewHolder, position: Int) {
        val item = items[position]
        Glide.with(holder.itemView.context).load(Constants.IMAGEBASE_URL + item.imageUrl).into(holder.ivItemImage)
    }

    override fun getItemCount(): Int = items.size
}
