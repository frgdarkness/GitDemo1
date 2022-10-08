package com.example.kmmnoteapp.datasource.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyImage(
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    @SerialName("download_url")
    val downloadUrl: String
)
