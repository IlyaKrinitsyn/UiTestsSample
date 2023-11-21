package ru.krinitsyn.uitestssample

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.krinitsyn.uitestssample.designsystem.DsCard
import ru.krinitsyn.uitestssample.designsystem.DsCell
import ru.krinitsyn.uitestssample.designsystem.DsTextPrimary
import ru.krinitsyn.uitestssample.designsystem.DsTitle
import ru.krinitsyn.uitestssample.resources.C

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .testTag(C.Screen.Main.name)
    ) {
        DsTitle(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            title = "Главный"
        )
        DsTextPrimary(
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp),
            title = "Описание раздела"
        )
        val cardsState = rememberLazyListState()
        LazyRow(
            state = cardsState,
            modifier = Modifier
                .padding(top = 16.dp)
                .testTag(C.Screen.Main.cardList),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            flingBehavior = rememberSnapFlingBehavior(lazyListState = cardsState)
        ) {
            itemsIndexed(listOf(1, 2, 3, 4, 5)) { index, item ->
                DsCard(
                    title = "Заголовок $item",
                    subtitle = "Подзаголовок",
                    modifier = Modifier.lazyListItemPosition(index)
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .weight(weight = 1.0f)
                .padding(top = 16.dp)
        ) {
            val items = List(size = 25) { it }
            itemsIndexed(items) { index, item ->
                DsCell(
                    title = "Итем $item",
                    subtitle = "Подзаголовок",
                    isDividerVisible = index != items.lastIndex
                )
            }

        }
    }
}

val LazyListCardPosition = SemanticsPropertyKey<Int>("LazyListCardPosition")
var SemanticsPropertyReceiver.lazyListCardPosition by LazyListCardPosition

fun Modifier.lazyListItemPosition(position: Int): Modifier {
    return semantics { lazyListCardPosition = position }
}

@Preview
@Composable
private fun Preview() {
    MainScreen()
}
