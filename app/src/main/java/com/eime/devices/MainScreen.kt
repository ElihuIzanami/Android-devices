import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eime.devices.ui.theme.DevicesTheme


@Composable
fun MainView(modifier: Modifier) {
    Text(text = "Comprar",
        modifier = modifier)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPreview() {
    DevicesTheme {
        MainView(Modifier.padding(top=24.dp))
    }
}