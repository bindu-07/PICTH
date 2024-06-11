package com.photo.picth.adapter

import com.photo.picth.presentation.BannerEditActivity
import com.photo.picth.ui.MainActivity
import com.photo.picth.ui.activities.bannerscreen.AchievementBannerInputActivity
import com.photo.picth.ui.activities.bannerscreen.BirthdayAnniversaryBannerInputActivity
import com.photo.picth.ui.activities.bannerscreen.BonanzaBannerInputActivity
import com.photo.picth.ui.activities.bannerscreen.CappingBannerInputActivity
import com.photo.picth.ui.activities.bannerscreen.CashBannerInputActivity
import com.photo.picth.ui.presentation.homepage.FestivalBannerEditActivity
import com.photo.picth.utils.ui.startNewActivity


class BannerAdapter(rv3Item: Int, val callFrom: String) :
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
            if (callFrom == "festival") {
                MainActivity.mInstance.startNewActivity(FestivalBannerEditActivity::class.java)

            }else if (callFrom == "bonan") {
                MainActivity.mInstance.startNewActivity(BonanzaBannerInputActivity::class.java)

            } else if (callFrom == "capping") {
                MainActivity.mInstance.startNewActivity(CappingBannerInputActivity::class.java)

            }else if (callFrom == "income") {
                MainActivity.mInstance.startNewActivity(CashBannerInputActivity::class.java)

            }else if (callFrom == "birth") {
                MainActivity.mInstance.startNewActivity(BirthdayAnniversaryBannerInputActivity::class.java)

            }else if (callFrom == "achieve") {
                MainActivity.mInstance.startNewActivity(AchievementBannerInputActivity::class.java)

            } else {
                MainActivity.mInstance.startNewActivity(BannerEditActivity::class.java)
            }

        }
    }


}

