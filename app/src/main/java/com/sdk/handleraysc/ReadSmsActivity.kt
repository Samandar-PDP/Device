package com.sdk.handleraysc

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class ReadSmsActivity : AppCompatActivity() {
    private val smsList: MutableList<String> = mutableListOf()
    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_sms)
        listView = findViewById(R.id.listView)

        readSms()
    }

    private fun readSms() {
        val cursor = contentResolver?.query(
            Uri.parse("content://sms/inbox"),
            null,
            null,
            null,
            null
        )
        while (cursor?.moveToNext()!!) {
            val number = cursor.getString(cursor.getColumnIndexOrThrow("address")).toString()
            val smsBody = cursor.getString(cursor.getColumnIndexOrThrow("body")).toString()
            smsList.add("Number: $number\nSms: $smsBody")
        }
        cursor.close()
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, smsList)
    }
}