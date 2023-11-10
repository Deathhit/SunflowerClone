package tw.com.deathhit.feature.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import tw.com.deathhit.domain.model.PhotoDO
import tw.com.deathhit.feature.gallery.databinding.ItemPhotoBinding
import tw.com.deathhit.feature.gallery.view_holder.PhotoViewHolder

class PhotoAdapter(
    private val glideRequestManager: RequestManager,
    private val onClickItemListener: (item: PhotoDO) -> Unit
) : PagingDataAdapter<PhotoDO, PhotoViewHolder>(comparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                item?.let(onClickItemListener)
            }
        }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.item = getItem(position)

        holder.item?.let { item ->
            bindAuthor(holder, item)
            bindImageUrl(holder, item)
        }
    }

    override fun onViewRecycled(holder: PhotoViewHolder) {
        super.onViewRecycled(holder)
        recycleImage(holder)
    }

    private fun bindAuthor(holder: PhotoViewHolder, item: PhotoDO) {
        holder.binding.textViewPhotographer.text = item.authorName
    }

    private fun bindImageUrl(holder: PhotoViewHolder, item: PhotoDO) {
        glideRequestManager.load(item.imageUrl).format(DecodeFormat.PREFER_RGB_565)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.binding.imageViewPlant)
    }

    private fun recycleImage(holder: PhotoViewHolder) {
        glideRequestManager.clear(holder.binding.imageViewPlant)
    }

    companion object {
        private val comparator = object : DiffUtil.ItemCallback<PhotoDO>() {
            override fun areItemsTheSame(
                oldItem: PhotoDO,
                newItem: PhotoDO
            ): Boolean =
                oldItem.plantId == newItem.plantId

            override fun areContentsTheSame(
                oldItem: PhotoDO,
                newItem: PhotoDO
            ): Boolean =
                oldItem == newItem
        }
    }
}