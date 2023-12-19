package ru.krinitsyn.uitestssample.screen

import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.core.BaseNode
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListItemNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListNode
import ru.krinitsyn.uitestssample.presentation.main.LazyListPosition
import ru.krinitsyn.uitestssample.ui.designsystem.resources.C

class SplashKScreen(
    semanticsProvider: SemanticsNodeInteractionsProvider
) : ComposeScreen<SplashKScreen>(
    semanticsProvider = semanticsProvider,
    viewBuilderAction = { hasTestTag(C.Screen.Splash.name) }
) {
    val button: KNode = child {
        hasTestTag(C.Screen.Splash.goToMainButton)
    }
}