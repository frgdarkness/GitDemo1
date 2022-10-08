package com.example.kmmnoteapp

import io.ktor.client.HttpClient

expect class HttpClientFactory {
    fun getHttpClient(): HttpClient
}