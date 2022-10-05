package com.masharo.pulser.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.masharo.pulser.presentation.vm.ConnectDeviceViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ConnectDeviceScreen() {
    val vm = getViewModel<ConnectDeviceViewModel>()
    Column {
        Button(onClick = vm::testBluetooth) {
            Text(text = "Проверить Bluetooth")
        }
        Button(onClick = vm::getListBluetooth) {
            Text(text = "Список подключений")
        }

        LazyColumn {

        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ConnectDeviceProvider() {
    
}