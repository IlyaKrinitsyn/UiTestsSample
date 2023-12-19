package ru.krinitsyn.uitestssample.data.mock

import okhttp3.Request
import java.lang.reflect.Method

data class MockRequest(
    val path: String,
    val method: String,
    val headers: Map<String, List<String>> = emptyMap(),
    //val body: String
    //val query: String
)

fun Request.toMockRequest(): MockRequest {
    val mockHeaders = mutableMapOf<String, List<String>>()
    repeat(headers.size) { index ->
        mockHeaders[headers.name(index)] = listOf(headers.value(index))
    }
    return MockRequest(
        path = url.encodedPath,
        method = method,
        headers = mockHeaders
    )
}
