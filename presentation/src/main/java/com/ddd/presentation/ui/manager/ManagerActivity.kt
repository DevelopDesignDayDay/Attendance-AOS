package com.ddd.presentation.ui.manager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ddd.presentation.R
import com.google.zxing.integration.android.IntentIntegrator

class ManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager)
        IntentIntegrator(this)
            .apply { setOrientationLocked(false) }
            .initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        (IntentIntegrator.parseActivityResult(requestCode, resultCode, data))?.let { result ->
            if (!result.contents.isNullOrEmpty()) Log.e("scan", result.contents.toString())
        } ?: super.onActivityResult(requestCode, resultCode, data)
    }
}
