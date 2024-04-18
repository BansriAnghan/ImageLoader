package com.bansri.imageloader.domain


import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.bansri.imageloader.data.NetworkEndpoints
import com.bansri.imageloader.data.ImagesModel
import io.reactivex.Observable

/**
 * Simple repository used as a proxy by the view models to fetch data.
 */
class Repository constructor(private val networkEndpoints: NetworkEndpoints) {

    fun loadPhotos(pageSize: Int): Observable<PagedList<ImagesModel>> {
        return RxPagedListBuilder(
            LoadImageDataFactory(networkEndpoints),
            PagedList.Config.Builder()
                .setInitialLoadSizeHint(pageSize)
                .setPageSize(pageSize)
                .build()
        ).buildObservable()
    }
}
