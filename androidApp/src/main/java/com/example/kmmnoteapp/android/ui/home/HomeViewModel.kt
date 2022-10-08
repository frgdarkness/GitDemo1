package com.example.kmmnoteapp.android.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmnoteapp.datasource.ImageRepository
import com.example.kmmnoteapp.datasource.data.DogImage
import com.example.kmmnoteapp.datasource.data.MyImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val repo: ImageRepository
    ) : ViewModel() {

    private var _imageList = MutableLiveData<List<MyImage>>()
    val imageList: LiveData<List<MyImage>> get() = _imageList

    fun loadRandomImage() {
        viewModelScope.launch {
            val data = repo.getMyImage()
            //Log.d("asd", "data: $data")

            //val data2 = repo.getMyImage()
            Log.d("asd", "myImages: ${data.joinToString("\n")}")
            _imageList.value = data
        }
    }

    fun saveImage(image: DogImage) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repo.saveImage(image)

            }
        }
    }

    fun getInfo() = "this is HomeViewModel"
}