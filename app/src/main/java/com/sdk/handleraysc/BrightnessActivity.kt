package com.sdk.handleraysc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.sdk.handleraysc.databinding.ActivityBrigthnessBinding

class BrightnessActivity : AppCompatActivity() {
    private var brightNessLevel = 0.0f
    private val binding by lazy { ActivityBrigthnessBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    brightNessLevel = progress / 10f
                    binding.textView.text = brightNessLevel.toString()
                    val layoutParams = window.attributes
                    layoutParams.screenBrightness = brightNessLevel
                    window.attributes = layoutParams
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}