package com.masharo.pulser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.masharo.pulser.ui.screen.MenuScreen
import com.masharo.pulser.ui.theme.PulserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PulserTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MenuScreen(
                        onClickViewData = {},
                        onClickUploadData = {},
                        onClickConnectDevice = {},
                        onClickSettings = {}
                    )
                }
            }
        }
    }
}