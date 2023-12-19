package ru.krinitsyn.uitestssample.data.mock

import okhttp3.Interceptor
import okhttp3.Response

class MockRequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        MockRequestDispatcher.proceed(chain.request())

}