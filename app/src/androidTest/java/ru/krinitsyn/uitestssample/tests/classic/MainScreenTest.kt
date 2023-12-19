package ru.krinitsyn.uitestssample.tests.classic

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.kakaocup.compose.node.element.ComposeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.krinitsyn.uitestssample.presentation.MainActivity
import ru.krinitsyn.uitestssample.screen.MainKScreen
import ru.krinitsyn.uitestssample.screen.SplashKScreen

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class MainScreenTest : TestCase(Kaspresso.Builder.withComposeSupport {}) {

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
