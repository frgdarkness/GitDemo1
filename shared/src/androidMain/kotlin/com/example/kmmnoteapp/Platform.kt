package com.example.kmmnoteapp

import com.example.kmmnoteapp.datasource.ImageRepositoryImpl

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}


actual fun showToast(a: String) {
    ImageRepositoryImpl

}