package ru.krinitsyn.uitestssample.base

import android.app.Instrumentation
import androidx.test.platform.app.InstrumentationRegistry
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import ru.krinitsyn.uitestssample.tests.mockwebserver.JsonUtil

abstract class MockWebServerTest(
    protected val webServer: MockWebServer = MockWebServer(),
    builder: Kaspresso.Builder = Kaspresso.Builder.withComposeSupport {
        beforeEachTest {
            webServer.start(8080)
        }
        afterEachTest {
            webServer.shutdown()
        }
    }
) : BaseTest(builder) {

    protected val jsonUtil = JsonUtil(InstrumentationRegistry.getInstrumentation())

}