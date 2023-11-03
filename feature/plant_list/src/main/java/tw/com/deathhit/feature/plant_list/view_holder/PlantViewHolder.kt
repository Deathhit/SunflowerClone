package tw.com.deathhit.feature.plant_list.view_holder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tw.com.deathhit.domain.model.PlantDO
import tw.com.deathhit.feature.plant_list.databinding.ItemPlantBinding

class PlantViewHolder(val binding: ItemPlantBinding, var item: PlantDO? = null) :
    ViewHolder(binding.root)