package tw.com.deathhit.feature.plant_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import tw.com.deathhit.domain.sunflower_clone.model.PlantDO
import tw.com.deathhit.feature.plant_list.databinding.ItemPlantBinding
import tw.com.deathhit.feature.plant_list.view_holder.PlantViewHolder

class PlantAdapter(
    private val glideRequestManager: RequestManager,
    private val onClickItemListener: (item: PlantDO) -> Unit
) : PagingDataAdapter<PlantDO, PlantViewHolder>(comparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder =
        PlantViewHolder(
            ItemPlantBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                item?.let(onClickItemListener)
            }
        }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.item = getItem(position)

        holder.item?.let { item ->
            bindImageUrl(holder, item)
            bindPlantName(holder, item)
        }
    }

    override fun onViewRecycled(holder: PlantViewHolder) {
        super.onViewRecycled(holder)
        recycleImage(holder)
    }

    private fun bindImageUrl(holder: PlantViewHolder, item: PlantDO) {
        glideRequestManager.load(item.imageUrl).format(DecodeFormat.PREFER_RGB_565)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.binding.imageViewPlant)
    }

    private fun bindPlantName(holder: PlantViewHolder, item: PlantDO) {
        holder.binding.textViewTitle.text = item.plantName
    }

    private fun recycleImage(holder: PlantViewHolder) {
        glideRequestManager.clear(holder.binding.imageViewPlant)
    }

    companion object {
        private val comparator = object : DiffUtil.ItemCallback<PlantDO>() {
            override fun areItemsTheSame(oldItem: PlantDO, newItem: PlantDO): Boolean =
                oldItem.plantId == newItem.plantId

            override fun areContentsTheSame(oldItem: PlantDO, newItem: PlantDO): Boolean =
                oldItem == newItem
        }
    }
}