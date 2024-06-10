package com.photo.picth.adapter

  import androidx.core.content.ContextCompat.startActivity
  import androidx.recyclerview.widget.RecyclerView
  import com.photo.picth.R
  import com.photo.picth.presentation.BannerEditActivity
  import com.photo.picth.presentation.LearnHowToUseActivity
  import com.photo.picth.ui.MainActivity
  import com.photo.picth.ui.presentation.profile.ProfileActivity
  import com.photo.picth.utils.ui.startNewActivity


class BannerAdapter(rv3Item: Int) :
    GenericAdapter<Any>(rv3Item) {



    override fun getItemCount(): Int {
        return 5

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //        val innerBinding = GroupTransactionsItemBinding.bind(holder.itemView)
//       Util.gradientText(innerBinding.tvAmount,"#FAD56E","#DEA020")
//        innerBinding.root.setOnClickListener {
//            if (position != RecyclerView.NO_POSITION) {
//                onItemClick(position)
//            }
//        }
        holder.itemView.setOnClickListener {
           MainActivity.mInstance.startNewActivity(BannerEditActivity::class.java)        }
    }


}

