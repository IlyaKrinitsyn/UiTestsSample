package ru.krinitsyn.uitestssample.designsystem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.krinitsyn.uitestssample.resources.C

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DsCard(
    title: String,
    subtitle: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .testTag(C.Tag.Ds.card),
        onClick = { onClick.invoke() },
        shape = RoundedCornerShape(size = 8.dp),
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .size(size = 160.dp)
                .padding(all = 16.dp)
        ) {
            Text(
                modifier = Modifier.testTag(C.Tag.title),
                text = title,
                fontSize = 16.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight(700),
                maxLines = 2
            )
            Text(
                modifier = Modifier
                    .testTag(C.Tag.subtitle)
                    .padding(top = 16.dp),
                text = subtitle,
                fontSize = 12.sp,
                fontStyle = FontStyle.Normal,
                color = Color.Gray,
                fontWeight = FontWeight(400),
                maxLines = 5
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DsCard(
        "Заголовок", "Подзаголовок"
    )
}