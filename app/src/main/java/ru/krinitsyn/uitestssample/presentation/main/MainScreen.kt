package ru.krinitsyn.uitestssample.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.krinitsyn.uitestssample.ui.designsystem.DsCard
import ru.krinitsyn.uitestssample.ui.designsystem.DsCell
import ru.krinitsyn.uitestssample.ui.designsystem.DsTextPrimary
import ru.krinitsyn.uitestssample.ui.designsystem.DsTitle
import ru.krinitsyn.uitestssample.ui.designsystem.resources.C

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
//    LaunchedEffect(Unit) {
//        viewModel.loadGists()
//    }

    val isGistsLoading by viewModel.isGistsLoadingFlow.collectAsStateWithLifecycle()
    val gists by viewModel.gistsFlow.collectAsStateWithLifecycle()

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
                .testTag(C.Screen.Main.cardsList),
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

        if (isGistsLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 1.0f)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(size = 36.dp)
                        .align(Alignment.Center)
                        .testTag(C.Screen.Main.gistsProgressBar)
                )
            }
        } else {
            if (gists.isNotEmpty()) {
                val scrollState = rememberLazyListState()
                val positionInLayout: Density.(Float, Float) -> Float = { _, _ -> 0f }
                val snappingLayout = remember(scrollState) { SnapLayoutInfoProvider(scrollState, positionInLayout) }
                val flingBehavior = rememberSnapFlingBehavior(snappingLayout)
                LazyColumn(
                    state = scrollState,
                    modifier = Modifier
                        .weight(weight = 1.0f)
                        .padding(top = 16.dp)
                        .testTag(C.Screen.Main.gistsList),
                    flingBehavior = flingBehavior
                ) {
                    itemsIndexed(gists) { index, gist ->
                        DsCell(
                            title = gist.description,
                            subtitle = gist.owner.login,
                            isDividerVisible = index != gists.lastIndex,
                            modifier = Modifier.lazyListItemPosition(index)
                        )
                    }
                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
                .testTag(C.Screen.Main.loadGistsButton),
            onClick = { viewModel.loadGists() }
        ) {
            DsTitle(title = "Загрузить")
        }
    }
}

val LazyListPosition = SemanticsPropertyKey<Int>("LazyListPosition")
var SemanticsPropertyReceiver.lazyListPosition by LazyListPosition

fun Modifier.lazyListItemPosition(position: Int): Modifier =
    semantics { lazyListPosition = position }

@Preview
@Composable
private fun Preview() {
//    MainScreen()
}
