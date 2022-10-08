package com.example.kmmnoteapp

class Greeting {
    fun greeting(): String {
//        CoroutineScope()

        return "Hello, ${Platform().platform}!"
    }
}