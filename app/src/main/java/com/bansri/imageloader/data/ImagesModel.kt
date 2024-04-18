package com.bansri.imageloader.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImagesModel(
    val id: String,
    val created_at: String,
    val width: Int,
    val height: Int,
    val color: String? = "#000000",
    val likes: Int,
    val description: String?,
    val urls: UrlsModel,
    val links: LinksModel,
    val user: UserModel
) : Parcelable
