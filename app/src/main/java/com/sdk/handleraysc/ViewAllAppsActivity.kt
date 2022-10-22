package com.sdk.handleraysc

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.sdk.handleraysc.databinding.ActivityViewAllAppsBinding
import com.sdk.handleraysc.ext.click

class ViewAllAppsActivity : AppCompatActivity() {
    private val mainList: MutableList<String> = mutableListOf()
    private val binding by lazy { ActivityViewAllAppsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button8.click {
            binding.progressBar.isVisible = true
            binding.button8.isVisible = false

            Handler(Looper.getMainLooper()).postDelayed({
                getAllApps()
            }, 1000)
        }
    }

    private fun getAllApps() {
        binding.progressBar.isVisible = false
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val appList = packageManager.queryIntentActivities(intent, 0)

        for (app in appList) {
            mainList.add(
                app.activityInfo.applicationInfo.loadLabel(packageManager).toString()
            )
        }
        binding.listView.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, mainList)
    }
}