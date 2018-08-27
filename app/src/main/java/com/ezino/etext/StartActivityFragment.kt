package com.ezino.etext

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_start.*

/**
 * A placeholder fragment containing a simple view.
 */
class StartActivityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(StartViewModel::class.java)
        viewModel.data.observe(this, Observer { msg -> textView.text = msg })

        fab.setOnClickListener {
            cameraView.captureImage { cameraKitImage -> viewModel.recognizeText(cameraKitImage) }
        }
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
