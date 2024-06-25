package com.photo.picth.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.photo.picth.R
import com.photo.picth.ui.presentation.bannerSettings.BannerSettingData
import com.photo.picth.ui.presentation.bannerSettings.BannerSettingItem
import com.photo.picth.ui.presentation.bannerSettings.Image
import com.photo.picth.utils.ui.Constants

class BannerSettingsTopUplineItemAdapter(private val items: List<Image>) : RecyclerView.Adapter<BannerSettingsTopUplineItemAdapter.BannerSettingsHolder>() {

    class BannerSettingsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivItemImage: ShapeableImageView = itemView.findViewById(R.id.load_one)
        //val tvtitle: TextView = itemView.findViewById(R.id.tv_title)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerSettingsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_banner_settings_top_upline, parent, false)
        return BannerSettingsHolder(view)
    }

    override fun onBindViewHolder(holder: BannerSettingsHolder, position: Int) {
        val item = items[position]
//        holder.tvtitle.text=item.title
        Glide.with(holder.itemView.context).load(Constants.IMAGEBASE_URL + item.path).into(holder.ivItemImage)
//        holder.itemView.setOnClickListener {
//            MainActivity.mInstance.startNewActivity(AchievementBannerInputActivity::class.java)
//
//        }
    }

    override fun getItemCount(): Int = items.size
}