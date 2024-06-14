package com.photo.picth.adapter

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.photo.picth.R
import com.photo.picth.ui.presentation.homepage.Category
import com.photo.picth.ui.presentation.homepage.Item

class CategoriesAdapter(private val categories: List<Category>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_CATEGORY = 0
        private const val VIEW_TYPE_ITEM = 1
    }

    private val items = mutableListOf<Any>()

    init {
        categories.forEach { category ->
            items.add(category.name)
            items.addAll(category.items)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] is String) VIEW_TYPE_CATEGORY else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_CATEGORY) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_category_name, parent, false)
            CategoryViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.rv3_item, parent, false)
            ItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_CATEGORY) {
            (holder as CategoryViewHolder).bind(items[position] as String)
        } else {
            (holder as ItemViewHolder).bind(items[position] as Item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvCategoryName: TextView = itemView.findViewById(R.id.tvCategoryName)

        fun bind(name: String) {
            tvCategoryName.text = name
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
//        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
//        private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)

        fun bind(item: Item) {
//            tvTitle.text = item.title
//            tvDescription.text = item.description
            Glide.with(itemView.context).load(item.imageUrl).into(imageView)
        }
    }
}
