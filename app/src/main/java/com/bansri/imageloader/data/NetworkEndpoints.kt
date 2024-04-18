package com.bansri.imageloader.data

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.bansri.imageloader.BuildConfig


/**
 * Retrofit endpoints definition.
 */
interface NetworkEndpoints {

    @GET("collections/317099/photos")
    fun loadPhotos(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Observable<Response<List<ImagesModel>>>
}
