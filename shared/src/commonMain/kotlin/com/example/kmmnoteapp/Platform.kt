package com.example.kmmnoteapp

expect class Platform() {
    val platform: String
}

expect fun showToast(a: String)