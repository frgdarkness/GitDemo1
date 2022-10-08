package com.example.kmmnoteapp.android.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kmmnoteapp.datasource.ImageRepository
import com.example.kmmnoteapp.datasource.data.DogImage

class FavoriteViewModel(private val repo: ImageRepository) : ViewModel() {

    private var _favoriteImageList = MutableLiveData<List<DogImage>>()
    val favoriteImageList: LiveData<List<DogImage>> get() = _favoriteImageList

    suspend fun loadRandomImage() {
        val data = repo.getAllImage()
        _favoriteImageList.value = data
    }
}