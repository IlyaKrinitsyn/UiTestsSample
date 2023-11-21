package ru.krinitsyn.uitestssample.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun DsCell(
    title: String,
    subtitle: String,
    isDividerVisible: Boolean,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = { onClick.invoke() }
    ) {
        Box(
            modifier = modifier
                .testTag(C.Tag.Ds.cell)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 56.dp)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight(400),
                    maxLines = 2
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = subtitle,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Normal,
                    color = Color.Gray,
                    fontWeight = FontWeight(400),
                    maxLines = 3
                )
            }
            if (isDividerVisible) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 1.dp)
                        .align(alignment = Alignment.BottomCenter)
                        .padding(horizontal = 16.dp)
                        .background(color = Color.LightGray)
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DsCell(
        "Заголовок",
        "Подзаголовок",
        true
    )
}