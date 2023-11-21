package ru.krinitsyn.uitestssample.scenario

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import io.github.kakaocup.compose.node.element.ComposeScreen
import ru.krinitsyn.uitestssample.screen.MainKScreen

@OptIn(ExperimentalTestApi::class)
class MainScreenScenario(composeTestRule: SemanticsNodeInteractionsProvider) : Scenario() {

    override val steps: TestContext<Unit>.() -> Unit = {
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
                    childAt<MainKScreen.LazyListCardNode>(4) {
                        verify("Заголовок 5", "Подзаголовок")
                        performClick()
                    }
                }
            }
        }
    }
}