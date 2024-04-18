package com.bansri.imageloader.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bansri.imageloader.domain.Repository

/**
 * View model factory for the photo screen.
 * This will use the repository to create the view model.
 */
class ImageLoaderViewModelFactory constructor(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageLoaderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ImageLoaderViewModel(repository) as T
        } else {
            throw IllegalArgumentException("unknown model class $modelClass")
        }
    }
}
