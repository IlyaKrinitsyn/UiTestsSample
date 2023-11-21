package ru.krinitsyn.uitestssample.designsystem

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.krinitsyn.uitestssample.resources.C

@Composable
fun DsTextPrimary(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .testTag(C.Tag.Ds.textPrimary),
        text = title,
        fontSize = 12.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight(400)
    )
}

@Preview
@Composable
private fun Preview() {
    DsTextPrimary(
        "Основной текст"
    )
}