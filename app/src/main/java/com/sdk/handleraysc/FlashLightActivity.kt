package com.sdk.handleraysc

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sdk.handleraysc.ext.click
import com.sdk.handleraysc.ext.toast

class FlashLightActivity : AppCompatActivity() {
    private var cameraId: String? = null
    private var isFlashAvailable = false
    private lateinit var cameraManager: CameraManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_light)
        val button: Button = findViewById(R.id.buttonFl)

        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        isFlashAvailable = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
        try {
            cameraId = cameraManager.cameraIdList[0] // 0 -> back camera // 1 -> front camera
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
        button.click {
            if (isFlashAvailable) {
                if (button.text.toString().contains("On")) {
                    button.text = "FlashLight Off"
                    flashLightOff()
                } else {
                    button.text = "FlashLight On"
                    flashLightOn()
                }
            } else {
                toast("FlashLight not available")
            }
        }
    }

    private fun flashLightOn() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId!!, true)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun flashLightOff() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId!!, false)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}