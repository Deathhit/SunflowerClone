package tw.com.deathhit.feature.gallery.view_holder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tw.com.deathhit.domain.sunflower_clone.model.PhotoDO
import tw.com.deathhit.feature.gallery.databinding.ItemPhotoBinding

class PhotoViewHolder(val binding: ItemPhotoBinding, var item: PhotoDO? = null) :
    ViewHolder(binding.root)