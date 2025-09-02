package tw.com.deathhit.feature.garden_planting_list.view_holder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tw.com.deathhit.domain.sunflower_clone.model.GardenPlantingDO
import tw.com.deathhit.feature.garden_planting_list.databinding.ItemGardenPlantingBinding

class GardenPlantingViewHolder(
    val binding: ItemGardenPlantingBinding,
    var item: GardenPlantingDO? = null
) : ViewHolder(binding.root)