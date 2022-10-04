package com.masharo.pulser.presentation.ui.screen

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.masharo.pulser.presentation.vm.ConnectDeviceViewModel
import org.koin.android.compat.ViewModelCompat.getViewModel

@Composable
fun ConnectDeviceScreen() {
    val vm = getViewModel<ConnectDeviceViewModel>()
    Button(onClick = vm::testBluetooth) {
        Text(text = "petty")
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ConnectDeviceProvider() {
    
}