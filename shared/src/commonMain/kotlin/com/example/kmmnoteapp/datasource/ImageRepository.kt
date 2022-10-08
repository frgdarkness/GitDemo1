package com.example.kmmnoteapp.datasource

import com.example.kmmnoteapp.DatabaseDriverFactory
import com.example.kmmnoteapp.HttpClientFactory
import com.example.kmmnoteapp.datasource.data.DogImage
import com.example.kmmnoteapp.datasource.data.MyImage
import com.example.kmmnoteapp.sqldelight.AppDatabase
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url

interface ImageRepository {

    suspend fun getRandomImage(): List<DogImage>

    suspend fun saveImage(image: DogImage)

    suspend fun getAllImage(): List<DogImage>

    suspend fun getMyImage(): List<MyImage>
}

class ImageRepositoryImpl(
    private val databaseDriverFactory: DatabaseDriverFactory,
    private val httpClientFactory: HttpClientFactory
) : ImageRepository {

    private val client = httpClientFactory.getHttpClient()

//    companion object {
//        private const val PIC_SUM_END_POINT = "https://picsum.photos/v2/list"
//    }

    override suspend fun getMyImage(): List<MyImage> {
        return try {
            client.get {
                url(PIC_SUM_END_POINT)
            }
        } catch (e: ClientRequestException) {
            println("error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            println("error: ${e.response.status.description}")
            emptyList()
        } catch (e: RedirectResponseException) {
            println("error: ${e.response.status.description}")
            emptyList()
        }
    }

    private val database = AppDatabase(databaseDriverFactory.createDriver())

    private val imageQueries = database.imageEntityQueries
    private val studentQueries = database.imageEntityQueries
    private val noteQueries = database.noteQueries

    override suspend fun saveImage(image: DogImage) {

        imageQueries.insertImage(image.id, image.url, image.width, image.height)
    }

    override suspend fun getAllImage(): List<DogImage> {
        return listOf()
//        return imageQueries.getAllImage().executeAsList().map { it.convertToDogImage() }
    }

    override suspend fun getRandomImage(): List<DogImage> {
        return try {
            client.get {
                url(IMAGES_ENDPOINT)
            }
        } catch (e: ClientRequestException) {
            println("error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            println("error: ${e.response.status.description}")
            emptyList()
        } catch (e: RedirectResponseException) {
            println("error: ${e.response.status.description}")
            emptyList()
        }
    }

    companion object {
        private const val IMAGES_ENDPOINT =
            "https://api.thecatapi.com/v1/images/search?limit=10&mime_type=image"

        private const val PIC_SUM_END_POINT = "https://picsum.photos/v2/list"

        private const val POST = "https://jsonplaceholder.typicode.com/posts"
    }
}