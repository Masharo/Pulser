package com.masharo.pulser.presentation

import android.bluetooth.BluetoothManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.masharo.pulser.presentation.ui.screen.ConnectDeviceScreen
import com.masharo.pulser.presentation.ui.screen.MenuScreen
import com.masharo.pulser.presentation.ui.screen.ScheduleScreen
import com.masharo.pulser.presentation.ui.theme.PulserTheme
import java.io.InputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PulserTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        startDestination = "menu",
                        navController = navController
                    ) {
                        composable("menu") {
                            MenuScreen(
                                onClickViewData = {},
                                onClickUploadData = {},
                                onClickConnectDevice = {
                                    navController.navigate("connect")
                                },
                                onClickSettings = {}
                            )
                        }
                        composable("connect") {
                            ConnectDeviceScreen(
                                owner = this@MainActivity as LifecycleOwner
                            )
                        }
                        composable("schedule") {
                            ScheduleScreen()
                        }
                    }
                }
            }
        }
    }

    fun test() {
        ContextCompat.getSystemService(applicationContext, BluetoothManager::class.java)!!.adapter.bondedDevices.let {
            val device = it.toList()[1]
            val connect = device.createRfcommSocketToServiceRecord(device.uuids[0].uuid)
            connect.connect()
            val read = (connect.inputStream as InputStream).read()
            Toast.makeText(applicationContext, read.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}