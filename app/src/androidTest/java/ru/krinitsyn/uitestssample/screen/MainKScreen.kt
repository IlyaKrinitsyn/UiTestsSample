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
import ru.krinitsyn.uitestssample.LazyListCardPosition
import ru.krinitsyn.uitestssample.resources.C

class MainKScreen(
    semanticsProvider: SemanticsNodeInteractionsProvider
) : ComposeScreen<MainKScreen>(
    semanticsProvider = semanticsProvider,
    viewBuilderAction = { hasTestTag(C.Screen.Main.name) }
) {
    val title: KNode = child {
        hasTestTag(C.Tag.Ds.title)
    }

    val subtitle: KNode = child {
        hasTestTag(C.Tag.Ds.textPrimary)
        hasText("Описание раздела")
    }

    val cardsList: KLazyListNode = KLazyListNode(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(C.Screen.Main.cardList) },
        itemTypeBuilder = {
            itemType(::LazyListCardNodeV2)
        },
        positionMatcher = { position ->
            SemanticsMatcher.expectValue(
                LazyListCardPosition,
                position
            )
        }
    )

    class LazyListCardNode(
        semanticsNode: SemanticsNode,
        semanticsProvider: SemanticsNodeInteractionsProvider,
    ) : KLazyListItemNode<LazyListCardNode>(semanticsNode, semanticsProvider) {

        private val title: KNode = child {
            useUnmergedTree = true
            hasTestTag(C.Tag.title)
        }

        private val subtitle: KNode = child {
            useUnmergedTree = true
            hasTestTag(C.Tag.subtitle)
        }

        fun verify(title: String, subtitle: String) {
            title {
                assertTextEquals(title)
                assertIsDisplayed()
            }
            subtitle {
                assertTextEquals(subtitle)
                assertIsDisplayed()
            }
        }
    }

    class LazyListCardNodeV2(
        semanticsNode: SemanticsNode,
        semanticsProvider: SemanticsNodeInteractionsProvider,
    ) : KLazyListItemNode<LazyListCardNode>(semanticsNode, semanticsProvider) {

        private val card = child<KCardNode> {
            useUnmergedTree = true
            hasTestTag(C.Tag.Ds.card)
        }

        fun verify(title: String, subtitle: String) {
            card.verify(title, subtitle)
        }

    }

    class KCardNode(
        semanticsProvider: SemanticsNodeInteractionsProvider,
        nodeMatcher: NodeMatcher,
        parentNode: BaseNode<*>? = null
    ) : BaseNode<KCardNode>(semanticsProvider, nodeMatcher, parentNode) {

        private val title = child<KNode> {
            useUnmergedTree = true
            hasTestTag(C.Tag.title)
        }

        private val subtitle = child<KNode> {
            useUnmergedTree = true
            hasTestTag(C.Tag.subtitle)
        }

        fun verify(title: String, subtitle: String) {
            title {
                assertTextEquals(title)
                assertIsDisplayed()
            }
            subtitle {
                assertTextEquals(subtitle)
                assertIsDisplayed()
            }
        }
    }
}