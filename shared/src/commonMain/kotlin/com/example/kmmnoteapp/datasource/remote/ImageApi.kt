package com.example.kmmnoteapp.datasource.remote

import com.example.kmmnoteapp.datasource.data.DogImage

interface ImageApi {

    suspend fun getRandomImage(): List<DogImage>

    suspend fun saveImage(image: DogImage)

    suspend fun getAllImage(): List<DogImage>
}