package com.example.kmmnoteapp.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kmmnoteapp.datasource.ImageRepository

class MyViewModelFactory(private val repo: ImageRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ImageRepository::class.java).newInstance(repo)
    }
}