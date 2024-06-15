import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.photo.picth.R
import com.photo.picth.adapter.CircleAdapter
import com.photo.picth.adapter.MotivationAdapter
import com.photo.picth.adapter.RvHorizontalBannerAdapter
import com.photo.picth.ui.presentation.homepage.CategoryItem

class CategoryAdapter(private val categories: List<CategoryItem>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCategoryName: TextView = itemView.findViewById(R.id.tvCategoryName)
        val rvItems: RecyclerView = itemView.findViewById(R.id.rvItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_category_name, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.tvCategoryName.text = category.name

//        // Set up the inner RecyclerView
//        holder.rvItems.apply {
//            layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
//            adapter = ItemAdapter(category.items)
//        }

        // Set up the inner RecyclerView with different adapters based on the position
        val adapter = when (position) {
            0 -> CircleAdapter(category.items) // Use CircleAdapter for position 0
            1 -> MotivationAdapter(category.items) // Use DifferentAdapter for position 1
            3 -> RvHorizontalBannerAdapter(category.items) // Use DifferentAdapter for position 1
            else -> ItemAdapter(category.items) // Use ItemAdapter for other positions
        }

        holder.rvItems.apply {
            layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
        }
    }

    override fun getItemCount(): Int = categories.size
}
