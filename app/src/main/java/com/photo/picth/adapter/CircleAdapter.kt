package com.photo.picth.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.photo.picth.R
import com.photo.picth.ui.MainActivity
import com.photo.picth.ui.activities.bannerscreen.AchievementBannerInputActivity
import com.photo.picth.ui.presentation.homepage.data.Item
import com.photo.picth.utils.ui.Constants
import com.photo.picth.utils.ui.startNewActivity

class CircleAdapter(private val items: List<Item>) :
    RecyclerView.Adapter<CircleAdapter.CircleViewHolder>() {

    class CircleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivItemImage: ImageView = itemView.findViewById(R.id.imageViewSpecial)
        val img: ImageView = itemView.findViewById(R.id.img)
        val tvtitle: TextView = itemView.findViewById(R.id.tv_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rvstories_item, parent, false)
        return CircleViewHolder(view)
    }

    override fun onBindViewHolder(holder: CircleViewHolder, position: Int) {
        val item = items[position]
        holder.tvtitle.text = item.title
        Glide.with(holder.itemView.context).load(Constants.IMAGEBASE_URL + item.imageUrl)
            .into(holder.ivItemImage)
        Glide.with(holder.itemView.context).load(Constants.IMAGEBASE_URL + item.imageUrl)
            .into(holder.img)
        holder.itemView.setOnClickListener {
            MainActivity.mInstance.startNewActivity(AchievementBannerInputActivity::class.java)

        }
    }

    override fun getItemCount(): Int = items.size
}
