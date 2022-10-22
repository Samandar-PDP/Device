package com.sdk.handleraysc

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.sdk.handleraysc.databinding.ActivityPhoneDetailsBinding

class PhoneDetailsActivity : AppCompatActivity() {
    private var phoneType: String = ""
    private val binding by lazy { ActivityPhoneDetailsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            readPhoneState()
        }
    }

    @SuppressLint("MissingPermission")
    private fun readPhoneState() {
        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        when (telephonyManager.phoneType) {
            TelephonyManager.PHONE_TYPE_GSM -> phoneType = "GSM" // Global System for Mobile
            TelephonyManager.PHONE_TYPE_CDMA -> phoneType = "CDMA" // Code division multiple access
            TelephonyManager.PHONE_TYPE_NONE -> phoneType = "NONE"
        }
        val stringBuilder = buildString {
            append("Roaming : ${telephonyManager.isNetworkRoaming}\n") // Chet elda yoki O'z davlatingizda ekanligingizni bildiradi
            append("Phone type: ${phoneType}\n") // Telefon tarmoq turi
            append("Voice Email Number: ${telephonyManager.voiceMailNumber}\n") // Ovozli xabar raqami
            append("IMEI Number: ${telephonyManager.deviceId}\n") // Xalqaro qurilma idsi // *#06#
            append("Sim country ISO: ${telephonyManager.simCountryIso}\n") // Xalqaro kod // Uz -> 998
            append("Network country ISO: ${telephonyManager.networkCountryIso}\n") //
            append("Device software version: ${telephonyManager.deviceSoftwareVersion}\n") // Device version
            append("Subscribe id: ${telephonyManager.subscriberId}\n") //
            append("Sim serial number: ${telephonyManager.simSerialNumber}") //
        }
        binding.textView.text = stringBuilder
    }
}