package com.bansri.imageloader.ui

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.bansri.imageloader.data.ImagesModel
import com.bansri.imageloader.databinding.ItemUnsplashPhotoBinding


/**
 * The photos recycler view adapter.
 * This is using the Android paging library to display an infinite list of photos.
 * This deals with either a single or multiple selection list.
 */
class PhotoAdapter constructor(context: Context) :
    PagedListAdapter<ImagesModel, PhotoAdapter.PhotoViewHolder>(COMPARATOR) {

    private var mOnPhotoSelectedListener: OnPhotoSelectedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemUnsplashPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)

    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        // item
        getItem(position)?.let { photo ->
            // image
            holder.binding.itemUnsplashPhotoImageView.aspectRatio = photo.height.toDouble() / photo.width.toDouble()
            holder.itemView.setBackgroundColor(Color.parseColor(photo.color))
            Picasso.get().load(photo.urls.small)
                .into(holder.binding.itemUnsplashPhotoImageView)
            // photograph name
            holder.binding.itemUnsplashPhotoTextView.text = photo.user.name

            // click listener
            holder.itemView.setOnClickListener {
                photo.urls.regular?.let {
                    mOnPhotoSelectedListener?.onPhotoClickPress(holder.binding.itemUnsplashPhotoImageView, it)
                }
            }
        }
    }

    fun setOnImageSelectedListener(onPhotoSelectedListener: OnPhotoSelectedListener) {
        mOnPhotoSelectedListener = onPhotoSelectedListener
    }

    companion object {
        // diff util comparator
        val COMPARATOR = object : DiffUtil.ItemCallback<ImagesModel>() {
            override fun areContentsTheSame(oldItem: ImagesModel, newItem: ImagesModel): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: ImagesModel, newItem: ImagesModel): Boolean =
                oldItem == newItem
        }
    }

    /**
     * UnsplashPhoto view holder.
     */
    class PhotoViewHolder(val binding: ItemUnsplashPhotoBinding) : RecyclerView.ViewHolder(binding.root)
}