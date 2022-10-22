package com.sdk.handleraysc

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.sdk.handleraysc.databinding.ActivityMainBinding
import com.sdk.handleraysc.ext.click
import com.sdk.handleraysc.ext.toast
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            viewButtons()
        } else {
            ActivityCompat.requestPermissions(
                this,
                permissions,
                10
            )
        }

        binding.apply {
            btn1.click {
                intent(PhoneDetailsActivity())
            }
            btn2.click {
                intent(FlashLightActivity())
            }
            btn3.click {
                intent(BrightnessActivity())
            }
            btn4.click {
                intent(ReadSmsActivity())
            }
            btn5.click {
                intent(ViewAllAppsActivity())
            }
            btn6.click {
                intent(VibrateActivity())
            }
            btn7.click {
                intent(WifiManagerActivity())
            }
            btn8.click {
                intent(BluetoothActivity())
            }
        }
    }

    private fun intent(clazz: Activity) {
        startActivity(Intent(this, clazz::class.java))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            viewButtons()
        } else {
            toast("Permission denied")
        }
    }

    private fun viewButtons() {
        binding.btn1.isVisible = true
        binding.btn2.isVisible = true
        binding.btn3.isVisible = true
        binding.btn4.isVisible = true
        binding.btn5.isVisible = true
        binding.btn6.isVisible = true
        binding.btn7.isVisible = true
        binding.btn8.isVisible = true
        binding.progressBar.isVisible = false
    }

    private val permissions = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.CAMERA,
        Manifest.permission.VIBRATE,
        Manifest.permission.READ_SMS
    )
}