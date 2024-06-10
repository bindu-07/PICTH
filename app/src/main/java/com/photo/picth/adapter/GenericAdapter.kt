package com.photo.picth.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


/** <M> is used for Data Class for specific type */
/** Pass a <M> in DiffUtil Class */


abstract class GenericAdapter<M : Any>(
    @LayoutRes private val layoutId: Int,
    private val limit: Int? = null
) :
    ListAdapter<M, GenericAdapter.ViewHolder>(GenericDiffUtil<M>()) {
    private var rvAttached: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        rvAttached = recyclerView
    }

    fun updateItem(index: Int, item: M?) {
        item ?: return
        val list = currentList.toMutableList()
        list[index] = item
        submitList(list)
        notifyItemChanged(index)
    }

    fun deleteItemAt(index: Int): M {
        val list = currentList.toMutableList()

        val item = list.removeAt(index)
        submitList(list)
        notifyItemRemoved(index)
        return item
    }

    fun deleteAll() {
        submitList(listOf())
    }

    fun insertItemAt(item: M, position: Int = itemCount) {
        val list = currentList.toMutableList()
        list.add(position, item)
        submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (limit != null) {
            if (limit < currentList.size) limit else currentList.size
        } else currentList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshAdapter() {
        notifyDataSetChanged()
    }

//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        try {
//            holder.view.setAnimation()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)


//    abstract fun onBindHolder(holder: T, dataClass: M)

    /** Animation Function */
    fun View.setAnimation(@AnimRes anim: Int) {
        val animation: Animation = AnimationUtils.loadAnimation(this.context, anim)
        this.startAnimation(animation)
    }

    fun moveItem(initialPosition: Int, finalPosition: Int) = kotlin.runCatching {
        val list = currentList.toMutableList()

        val temp = list.removeAt(initialPosition)
        insertItemAt(temp, finalPosition)
        submitList(list)
    }

    fun getAllItems(): ArrayList<M> {
        return ArrayList<M>().apply {
            addAll(currentList)
        }
    }

    override fun getItem(position: Int): M {
        return super.getItem(position)!!
    }
}
