package com.photo.picth.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.photo.picth.R
import com.photo.picth.ui.presentation.homepage.data.SlideBanner
import com.photo.picth.utils.ui.Constants

class ImageSliderAdapter(context: FragmentActivity, private val images: List<SlideBanner>) : RecyclerView.Adapter<ImageSliderAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Assuming images list contains objects of type Item and Item has a method or property to get the image resource
       // holder.imageView.setImageResource(images[position].imageUrl)
        Glide.with(holder.itemView.context).load(Constants.IMAGEBASE_URL + images[position].imageUrl).into(holder.imageView)

    }

    override fun getItemCount(): Int = images.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imgSliderbaner)
    }
}
