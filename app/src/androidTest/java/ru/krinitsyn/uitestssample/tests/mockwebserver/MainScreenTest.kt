package ru.krinitsyn.uitestssample.tests.mockwebserver

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.kakaocup.compose.node.element.ComposeScreen
import kotlinx.serialization.json.Json
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.krinitsyn.uitestssample.base.MockWebServerTest
import ru.krinitsyn.uitestssample.presentation.MainActivity
import ru.krinitsyn.uitestssample.screen.MainKScreen
import ru.krinitsyn.uitestssample.screen.SplashKScreen
import java.net.HttpURLConnection

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class MainScreenTest : MockWebServerTest() {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test() {
        before {
            webServer.dispatcher = object : Dispatcher() {
                override fun dispatch(request: RecordedRequest): MockResponse =
                    when {
                        request.path?.contains("gists/public") == true ->
                            MockResponse()
                                .setResponseCode(HttpURLConnection.HTTP_OK)
                                .setBody(jsonUtil.readJSONFromAsset("gists_public.json"))

                        else ->
                            MockResponse()
                                .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
                    }
            }
        }.after {
        }.run {
            step("Open splash screen and go to main") {
                ComposeScreen.onComposeScreen<SplashKScreen>(composeTestRule) {
                    button {
                        assertIsDisplayed()
                        performClick()
                    }
                }
            }
            flakySafely {
                step("Open main screen") {
                    ComposeScreen.onComposeScreen<MainKScreen>(composeTestRule) {
                        title {
                            assertTextEquals("Главный")
                            assertIsDisplayed()
                        }
                        subtitle {
                            assertIsDisplayed()
                        }
                        cardsList {
                            childAt<MainKScreen.LazyListCardNodeV2>(4) {
                                performClick()
                            }
                        }
                        loadGistsButton {
                            performClick()
                        }
                        gistsList {
                            childAt<MainKScreen.LazyListGistNode>(0) {
                                assertIsDisplayed()
                            }
                        }
                    }
                }
            }
        }
    }

}