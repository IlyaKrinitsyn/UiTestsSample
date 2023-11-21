package ru.krinitsyn.uitestssample.tests

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.components.composesupport.interceptors.behavior.impl.systemsafety.SystemDialogSafetySemanticsBehaviorInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.compose.node.element.ComposeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.krinitsyn.uitestssample.MainActivity
import ru.krinitsyn.uitestssample.screen.MainKScreen

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class MainScreenTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport()
) {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test() {
        before {

        }.after {

        }.run {
            step("Open main screen") {
                composeTestRule.onRoot(true).printToLog("Krinitsyn")
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
                            verify("Заголовок 5", "Подзаголовок")
                            performClick()
                        }
                    }
                }
            }
        }
    }

}