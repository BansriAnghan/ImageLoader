package com.bansri.imageloader.ui

import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.jakewharton.rxbinding2.widget.RxTextView
import com.bansri.imageloader.ImageLoader
import com.bansri.imageloader.data.ImagesModel
import com.bansri.imageloader.domain.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * View model for the picker screen.
 * This will use the repository to fetch the photos depending on the search criteria.
 * This is using rx binding.
 */
class ImageLoaderViewModel constructor(private val repository: Repository) : BaseViewModel() {

    private val mPhotosLiveData = MutableLiveData<PagedList<ImagesModel>>()
    val photosLiveData: LiveData<PagedList<ImagesModel>> get() = mPhotosLiveData

    override fun getTag(): String {
        return ImageLoaderViewModel::class.java.simpleName
    }

    /**
     * Binds the edit text using rx binding to listen to text change.
     *
     * @param editText the edit text to listen to
     */
    fun bindSearch(editText: EditText) {
        RxTextView.textChanges(editText)
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                mLoadingLiveData.postValue(true)
            }
            .observeOn(Schedulers.io())
            .switchMap { text ->
                repository.loadPhotos(ImageLoader.getPageSize())
            }
            .subscribe(object : BaseObserver<PagedList<ImagesModel>>() {
                override fun onSuccess(data: PagedList<ImagesModel>?) {
                    mPhotosLiveData.postValue(data)
                }
            })
    }
}
