package com.example.kmmnoteapp.datasource.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DogImage(
    @SerialName("id")
    val id: String,
    @SerialName("url")
    val url: String,
    @SerialName("width")
    val width: Long,
    @SerialName("height")
    val height: Long
)