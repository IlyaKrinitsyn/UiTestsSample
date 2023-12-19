package ru.krinitsyn.uitestssample.data.mock

import okhttp3.Request
import okhttp3.Response

object MockRequestDispatcher {

    private val mocks: MutableMap<MockRequest, MockResponse> = mutableMapOf()

    fun proceed(request: Request): Response {
        val mockRequest = request.toMockRequest()
        return mocks[mockRequest]?.toResponse(request)
            ?: throw IllegalStateException("Не нашли ответ на реквест: $mockRequest")
    }

    fun add(mockRequest: MockRequest, mockResponse: MockResponse) {
        mocks[mockRequest] = mockResponse
    }

}