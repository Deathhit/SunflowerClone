package tw.com.deathhit.feature.garden_planting_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DecodeFormat
import tw.com.deathhit.domain.model.GardenPlantingDO
import tw.com.deathhit.feature.garden_planting_list.databinding.ItemGardenPlantingBinding
import tw.com.deathhit.feature.garden_planting_list.view_holder.GardenPlantingViewHolder
import java.text.SimpleDateFormat
import java.util.Locale

class GardenPlantingAdapter(
    private val glideRequestManager: RequestManager,
    private val onClickItemListener: (item: GardenPlantingDO) -> Unit
) : PagingDataAdapter<GardenPlantingDO, GardenPlantingViewHolder>(comparator) {
    private val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardenPlantingViewHolder =
        GardenPlantingViewHolder(
            ItemGardenPlantingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                item?.let(onClickItemListener)
            }
        }

    override fun onBindViewHolder(holder: GardenPlantingViewHolder, position: Int) {
        holder.item = getItem(position)

        holder.item?.let { item ->
            bindImageUrl(holder, item)
            bindPlantDate(holder, item)
            bindPlantName(holder, item)
            bindWaterIntervalDays(holder, item)
        }
    }

    override fun onViewRecycled(holder: GardenPlantingViewHolder) {
        super.onViewRecycled(holder)
        recycleImage(holder)
    }

    private fun bindImageUrl(holder: GardenPlantingViewHolder, item: GardenPlantingDO) {
        glideRequestManager.load(item.imageUrl).format(DecodeFormat.PREFER_RGB_565)
            .into(holder.binding.imageViewPlant)
    }

    private fun bindPlantDate(holder: GardenPlantingViewHolder, item: GardenPlantingDO) {
        holder.binding.textViewPlantDateValue.text = dateFormat.format(item.plantDate)
    }

    private fun bindPlantName(holder: GardenPlantingViewHolder, item: GardenPlantingDO) {
        holder.binding.textViewTitle.text = item.plantName
    }

    private fun bindWaterIntervalDays(holder: GardenPlantingViewHolder, item: GardenPlantingDO) {
        with(holder.binding) {
            val context = root.context

            textViewWaterInterval.text = item.wateringIntervalDays?.let { days ->
                context.getString(R.string.garden_planting_list_water_in_x_days, days)
            } ?: ""
        }
    }

    private fun recycleImage(holder: GardenPlantingViewHolder) {
        glideRequestManager.clear(holder.binding.imageViewPlant)
    }

    companion object {
        private val comparator = object : DiffUtil.ItemCallback<GardenPlantingDO>() {
            override fun areItemsTheSame(
                oldItem: GardenPlantingDO,
                newItem: GardenPlantingDO
            ): Boolean =
                oldItem.plantId == newItem.plantId

            override fun areContentsTheSame(
                oldItem: GardenPlantingDO,
                newItem: GardenPlantingDO
            ): Boolean =
                oldItem == newItem
        }
    }
}