package com.example.binlist.ui.navigation

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

class MapNavigator(private val context: Context) {

    fun openMap(latitude: Double, longitude: Double) {
        val uri = "geo:$latitude,$longitude?q=$latitude,$longitude".toUri()
        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}