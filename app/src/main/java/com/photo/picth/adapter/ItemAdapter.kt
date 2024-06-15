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
import com.photo.picth.ui.presentation.homepage.Item
import com.photo.picth.utils.ui.Constants
import com.photo.picth.utils.ui.startNewActivity

class ItemAdapter(private val items: List<Item>) : RecyclerView.Adapter<ItemAdapter.DefaultViewHolder>() {

    // ViewHolder for default layout
    class DefaultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivItemImage: ImageView = itemView.findViewById(R.id.imageViewSpecial)
        val tvItemDescription: TextView = itemView.findViewById(R.id.tv_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rvfestival_item, parent, false)
        return DefaultViewHolder(view)
    }

    override fun onBindViewHolder(holder: DefaultViewHolder, position: Int) {
        val item = items[position]
        // Load image using Glide
        Glide.with(holder.itemView.context).load(Constants.IMAGEBASE_URL + item.imageUrl).into(holder.ivItemImage)
        // Set the item description
        holder.tvItemDescription.text = item.description
        holder.itemView.setOnClickListener {
            MainActivity.mInstance.startNewActivity(AchievementBannerInputActivity::class.java)

        }
    }

    override fun getItemCount(): Int = items.size
}
