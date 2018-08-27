package com.ezino.etext

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.wonderkiln.camerakit.CameraKitImage

class StartViewModel : ViewModel() {

    private val _data = MutableLiveData<String>()
    val data: LiveData<String>
        get() = _data

    init {
        _data.value = "Hello, Jetpack!"
    }

    fun recognizeText(cameraKitImage: CameraKitImage) {
        val image = FirebaseVisionImage.fromBitmap(cameraKitImage.bitmap)
        val textRecognizer = FirebaseVision.getInstance().onDeviceTextRecognizer
        textRecognizer.processImage(image)
                .addOnSuccessListener {
                    // Task completed successfully
                    // ...
                    val resultText = it.text
                }
                .addOnFailureListener {
                    // Task failed with an exception
                    // ...
                }
    }
}