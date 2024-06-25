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

class MotivationAdapter(private val items: List<Item>) : RecyclerView.Adapter<MotivationAdapter.CircleViewHolder>() {

    class CircleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivItemImage: ImageView = itemView.findViewById(R.id.imageViewSpecial)
        val tvtitle: TextView = itemView.findViewById(R.id.tv_title)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_moti_item, parent, false)
        return CircleViewHolder(view)
    }

    override fun onBindViewHolder(holder: CircleViewHolder, position: Int) {
        val item = items[position]
        holder.tvtitle.text=item.title
        Glide.with(holder.itemView.context).load(Constants.IMAGEBASE_URL + item.imageUrl).into(holder.ivItemImage)
        holder.itemView.setOnClickListener {
            MainActivity.mInstance.startNewActivity(AchievementBannerInputActivity::class.java)

        }
    }

    override fun getItemCount(): Int = items.size
}
