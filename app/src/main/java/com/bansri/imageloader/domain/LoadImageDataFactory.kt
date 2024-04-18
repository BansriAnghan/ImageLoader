package com.bansri.imageloader.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.bansri.imageloader.data.NetworkEndpoints
import com.bansri.imageloader.data.ImagesModel

/**
 * Android paging library data source factory.
 * This will create the load photo data source.
 */
class LoadImageDataFactory constructor(private val networkEndpoints: NetworkEndpoints) :
    DataSource.Factory<Int, ImagesModel>() {

    val sourceLiveData = MutableLiveData<LoadImageData>()

    override fun create(): DataSource<Int, ImagesModel> {
        val source = LoadImageData(networkEndpoints)
        sourceLiveData.postValue(source)
        return source
    }
}
