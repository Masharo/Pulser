package com.masharo.pulser.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.masharo.pulser.R
import com.masharo.pulser.ui.theme.LoveTea
import com.masharo.pulser.ui.theme.PulserTheme

@Composable
fun MenuScreen(
    onClickViewData: () -> Unit,
    onClickUploadData: () -> Unit,
    onClickConnectDevice: () -> Unit,
    onClickSettings: () -> Unit,
) {
    Column {

        Row {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(
                    id = R.drawable.cordi
                ),
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )
        }

        Row {
            Button(
                onClick = onClickViewData,
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(0.5f)
                    .padding(25.dp),
                colors = buttonColors(
                    backgroundColor = LoveTea,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(id = R.string.view_data_main),
                    fontSize = 17.sp
                )
            }

            Button(
                onClick = onClickUploadData,
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth()
                    .padding(25.dp),
                colors = buttonColors(
                    backgroundColor = LoveTea,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(id = R.string.load_data_main),
                    fontSize = 17.sp
                )
            }
        }

        Row {
            Button(
                onClick = onClickConnectDevice,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f)
                    .padding(25.dp),
                colors = buttonColors(
                    backgroundColor = LoveTea,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(id = R.string.connect_device_main),
                    fontSize = 17.sp
                )
            }

            Button(
                onClick = onClickSettings,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(25.dp),
                colors = buttonColors(
                    backgroundColor = LoveTea,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(id = R.string.settings_main),
                    fontSize = 17.sp
                )
            }
        }

    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MenuPreview() {
    PulserTheme {
        MenuScreen(
            onClickConnectDevice = {},
            onClickSettings = {},
            onClickUploadData = {},
            onClickViewData = {},
        )
    }
}