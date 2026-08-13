import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.eime.devices.Device
import com.eime.devices.Specs
import com.eime.devices.ui.theme.DevicesTheme
import androidx.compose.material3.Icon
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.eime.devices.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eime.devices.ui.theme.Typography


@Composable
fun DeviceItemView(device: Device) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 16.dp, top = 8.dp, bottom = 8.dp, )) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Wall Art Illustration",
            //modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Column() {
            Text(text= device.name,
                style = Typography.headlineMedium
            )
            if(device.data?.color != null) {
                Text(
                    text= device.data.color,
                    style = Typography.bodyMedium
                )
            }

            if(device.data?.capacity != null) {
                Text(
                    text= device.data.capacity,
                    style = Typography.bodyMedium
                )
            }

            if(device.data?.price != null) {
                Text(
                    text= "$${device.data.price}",
                    style = Typography.bodyMedium
                )
            }

            Text(text= device.data?.color ?:"-", style = Typography.bodyMedium)
            Text(text= device.data?.capacity ?:"-", style = Typography.bodyMedium)

            HorizontalDivider()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DeviceItemPreview(){
    DevicesTheme() {
        DeviceItemView(device = Device(1, "Nexus", Specs("Black", "64GB", price = 145.15f)))
    }
}
