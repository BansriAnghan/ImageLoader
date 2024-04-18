package com.bansri.imageloader.ui

import android.widget.ImageView

interface OnPhotoSelectedListener {

    /**
     * When the user presses an photo.
     *
     * @param imageView the pressed image view
     * @param url the url of the photo
     */
    fun onPhotoClickPress(imageView: ImageView, url: String)
}
