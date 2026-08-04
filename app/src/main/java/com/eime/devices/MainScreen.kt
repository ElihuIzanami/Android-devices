import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eime.devices.ui.theme.DevicesTheme
import com.eime.devices.ui.theme.Typography


@Composable
fun MainView(modifier: Modifier) {
    Text(text = "Comprar",
        //modifier = modifier.background(Color.Cyan).padding(20.dp).fillMaxWidth(),
        modifier = modifier.fillMaxWidth(),
        style = Typography.displayMedium,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPreview() {
    DevicesTheme {
        MainView(Modifier.padding(top=24.dp))
    }
}