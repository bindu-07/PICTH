package com.photo.picth.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.photo.picth.R
import com.photo.picth.ui.MainActivity
import com.photo.picth.ui.activities.bannerscreen.AchievementBannerInputActivity
import com.photo.picth.ui.presentation.homepage.data.AdsBanner
import com.photo.picth.utils.ui.Constants
import com.photo.picth.utils.ui.startNewActivity

class BottomBannerAdapter(private val items: List<AdsBanner>) :
    RecyclerView.Adapter<BottomBannerAdapter.CircleViewHolder>() {

    class CircleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivItemImage: ImageView = itemView.findViewById(R.id.imgSliderbaner)
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_adsbanner, parent, false)
        return CircleViewHolder(view)
    }

    override fun onBindViewHolder(holder: CircleViewHolder, position: Int) {
        val item = items[position]
        Glide.with(holder.itemView.context).load(Constants.IMAGEBASE_URL + item.imageUrl)
            .into(holder.ivItemImage)
        holder.itemView.setOnClickListener {
            MainActivity.mInstance.startNewActivity(AchievementBannerInputActivity::class.java)

        }
    }

    override fun getItemCount(): Int = items.size
}
