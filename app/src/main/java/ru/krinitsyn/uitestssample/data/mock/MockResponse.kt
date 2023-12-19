package ru.krinitsyn.uitestssample.data.mock

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.asResponseBody
import okio.Buffer
import java.io.InputStream

data class MockResponse(
    val responseCode: Int,
    val responseBody: MockResponseData
)

fun MockResponse.toResponse(request: Request): Response = Response.Builder()
    .request(request)
    .protocol(Protocol.HTTP_1_1)
    .code(responseCode)
    .message("OK")
    .also { builder ->
        val buffer = Buffer()
        buffer.readFrom(responseBody.getResponseData())
        builder.body(buffer.asResponseBody(null, buffer.size))
    }
    .build()