package com.example.kmmnoteapp.datasource.remote

import com.example.kmmnoteapp.sqldelight.AppDatabase
import com.example.kmmnoteapp.DatabaseDriverFactory
import com.example.kmmnoteapp.HttpClientFactory
import com.example.kmmnoteapp.datasource.data.DogImage
import io.ktor.client.request.get

class ImageApiService(
    private val databaseDriverFactory: DatabaseDriverFactory,
    private val httpClientFactory: HttpClientFactory
) : ImageApi {

    companion object {
        private const val IMAGES_ENDPOINT =
            "https://api.thecatapi.com/v1/images/search?limit=10&mime_type=image"
    }

    private val client = httpClientFactory.getHttpClient()
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val imageQueries = database.imageEntityQueries
    private val noteQueries = database.noteQueries

    override suspend fun getRandomImage(): List<DogImage> {
        return client.get(IMAGES_ENDPOINT)
    }

    override suspend fun saveImage(image: DogImage) {
        imageQueries.insertImage(
            id = image.id,
            url = image.url,
            width = image.width,
            height = image.height
        )
    }

    override suspend fun getAllImage(): List<DogImage> {
        TODO("Not yet implemented")
    }
}