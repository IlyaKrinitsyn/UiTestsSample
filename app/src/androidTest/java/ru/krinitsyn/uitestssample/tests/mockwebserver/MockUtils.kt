package ru.krinitsyn.uitestssample.tests.mockwebserver

import android.app.Application
import android.app.Instrumentation
import android.content.res.AssetManager
import java.io.IOException


class JsonUtil(
    private val instrumentation: Instrumentation
) {
    fun readJSONFromAsset(fileName: String): String = try {
        val inputStream = (instrumentation.targetContext.applicationContext.assets as AssetManager).open(fileName)
        inputStream.bufferedReader().use { it.readText() }
    } catch (ex: IOException) {
        ex.printStackTrace()
        ""
    }

}
