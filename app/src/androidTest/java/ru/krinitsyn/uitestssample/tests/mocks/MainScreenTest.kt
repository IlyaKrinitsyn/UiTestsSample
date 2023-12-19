package ru.krinitsyn.uitestssample.tests.mocks

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.kakaocup.compose.node.element.ComposeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.krinitsyn.uitestssample.R
import ru.krinitsyn.uitestssample.data.mock.AssetMockResponseData
import ru.krinitsyn.uitestssample.data.mock.MockRequest
import ru.krinitsyn.uitestssample.data.mock.MockRequestDispatcher
import ru.krinitsyn.uitestssample.data.mock.MockResponse
import ru.krinitsyn.uitestssample.data.mock.MockResponseData
import ru.krinitsyn.uitestssample.data.mock.RawFileMockResponseData
import ru.krinitsyn.uitestssample.presentation.MainActivity
import ru.krinitsyn.uitestssample.screen.MainKScreen
import ru.krinitsyn.uitestssample.screen.SplashKScreen

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class MainScreenTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        beforeEachTest {
            MockRequestDispatcher
                .add(
                    MockRequest(
                        path = "/gists/public",
                        method = "GET",
                        headers = emptyMap(),
                    ),
                    MockResponse(
                        responseCode = 200,
                        responseBody = RawFileMockResponseData(
                            rawResId = R.raw.gists_public,
                            instrumentation = InstrumentationRegistry.getInstrumentation(),
                        )
//                        responseBody = AssetMockResponseData(
//                            assetName = "gists_public.json",
//                            instrumentation = InstrumentationRegistry.getInstrumentation(),
//                        )
                    )
                )
        }
    }
) {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test() {
        before {
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
