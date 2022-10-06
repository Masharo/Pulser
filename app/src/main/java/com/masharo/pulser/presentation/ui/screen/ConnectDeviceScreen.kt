package com.masharo.pulser.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.masharo.pulser.presentation.model.Device
import com.masharo.pulser.presentation.ui.theme.LightGray
import com.masharo.pulser.presentation.ui.theme.Selected
import com.masharo.pulser.presentation.vm.ConnectDeviceViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ConnectDeviceScreen(owner: LifecycleOwner) {
    val listDevice = remember {
        mutableStateOf<List<Device>>(listOf())
    }

    val vm = getViewModel<ConnectDeviceViewModel>()
    vm.deviceList.observe(owner) {
        listDevice.value = it
    }

    Column(
        modifier = Modifier.
            padding(10.dp)
    ) {
        Button(onClick = vm::testBluetooth) {
            Text(text = "Проверить Bluetooth")
        }
        Button(onClick = vm::getListBluetooth) {
            Text(text = "Список подключений")
        }

        LazyColumn {
            items(listDevice.value) {
                DeviceItem(
                    title = it.name,
                    mac = it.mac,
                    isSelect = it.isSelect
                )
            }
        }
    }
}

@Composable
fun DeviceItem(
    title: String,
    mac: String,
    isSelect: Boolean
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        backgroundColor = LightGray
    ) {
        Row(
            modifier = Modifier
                .padding(
                    top = 5.dp,
                    bottom = 5.dp,
                    start = 5.dp,
                    end = 10.dp
                )

        ) {
            Column(
                modifier = Modifier.weight(1F)
            ) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = mac,
                    style = MaterialTheme.typography.body1
                )

            }

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(25.dp)
                    .background(Color.Gray)
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelect) Selected
                        else LightGray
                    )
                    .align(Alignment.CenterVertically)

            )
        }
    }

}

@Preview(
    showBackground = true,
    name = "NotConnected"
)
@Composable
fun ItemCPreview() {
    DeviceItem(
        title = "Title",
        mac = "00:11:22:33:44:55",
        isSelect = true
    )
}

@Preview(
    showBackground = true,
    name = "Connected"
)
@Composable
fun ItemNCPreview() {
    DeviceItem(
        title = "Title",
        mac = "00:11:22:33:44:55",
        isSelect = false
    )
}