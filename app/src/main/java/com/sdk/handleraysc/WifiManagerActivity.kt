package com.sdk.handleraysc

import android.content.Context
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.Formatter
import com.sdk.handleraysc.databinding.ActivityWifiManagerBinding
import com.sdk.handleraysc.ext.click
import com.sdk.handleraysc.ext.toast
import com.sdk.handleraysc.util.NetworkUtils

class WifiManagerActivity : AppCompatActivity() {
    private val binding by lazy { ActivityWifiManagerBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        val networkUtils = NetworkUtils(this)
        toast("${networkUtils.isNetworkConnected()}")

        binding.switchCompat.click {
            if (binding.switchCompat.isChecked) {
                binding.switchCompat.text = "Wifi On"
                wifiManager.isWifiEnabled = true
            } else  {
                binding.switchCompat.text = "Wifi Off"
                wifiManager.isWifiEnabled = false
            }
        }
        binding.button.click {
            val wInfo = wifiManager.connectionInfo
            val ipAddress = Formatter.formatIpAddress(wInfo.ipAddress)
            val linkSpeed = wInfo.linkSpeed
            val networkID = wInfo.networkId
            val ssid = wInfo.ssid
            val hSsid = wInfo.hiddenSSID
            val bssid = wInfo.bssid
            val string = buildString {
                append("IP Address:\t$ipAddress\n")
                append("Link Speed:\t$linkSpeed\n")
                append("Network ID:\t$networkID\n")
                append("SSID:\t$ssid\n")
                append("Hidden SSID:\t$hSsid\n")
                append("BSSID:\t$bssid\n")
            }
            binding.textView.text = string
        }
    }
}