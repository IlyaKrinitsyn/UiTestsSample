package ru.krinitsyn.uitestssample.ui.designsystem

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.krinitsyn.uitestssample.ui.designsystem.resources.C


@Composable
fun DsTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .testTag(C.Tag.Ds.title),
        text = title,
        fontSize = 24.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight(700)
    )
}

@Preview
@Composable
private fun Preview() {
    DsTitle(
        "Заголовок"
    )
}