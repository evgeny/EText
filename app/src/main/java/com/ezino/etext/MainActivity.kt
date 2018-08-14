package com.ezino.etext

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        cameraView.captureImage { cameraKitImage ->
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab_take_photo.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()

        cameraView.start()
    }

    override fun onPause() {
        cameraView.stop()

        super.onPause()
    }
}
