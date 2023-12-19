package ru.krinitsyn.uitestssample.data.mock

import android.app.Instrumentation
import androidx.annotation.RawRes
import java.io.InputStream

interface MockResponseData {

    fun getResponseData(): InputStream

}

class RawFileMockResponseData(
    @RawRes private val rawResId: Int,
    private val instrumentation: Instrumentation,
) : MockResponseData {
    override fun getResponseData(): InputStream =
        instrumentation.targetContext.resources.openRawResource(rawResId)

    override fun equals(other: Any?): Boolean =
        rawResId == (other as? RawFileMockResponseData)?.rawResId

    override fun hashCode(): Int = rawResId
}


class AssetMockResponseData(
    private val assetName: String,
    private val instrumentation: Instrumentation,
) : MockResponseData {
    override fun getResponseData(): InputStream =
        instrumentation.targetContext.applicationContext.assets.open(assetName)

    override fun equals(other: Any?): Boolean =
        assetName == (other as? AssetMockResponseData)?.assetName

    override fun hashCode(): Int = assetName.hashCode()
}
