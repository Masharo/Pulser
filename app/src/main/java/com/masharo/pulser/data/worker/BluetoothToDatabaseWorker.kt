package com.masharo.pulser.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.masharo.pulser.data.bluetoothService.BluetoothService
import com.masharo.pulser.data.database.PulserStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.BufferedReader
import java.io.InputStreamReader

class BluetoothToDatabaseWorker(
    val context: Context,
    params: WorkerParameters
): CoroutineWorker(context, params), KoinComponent {

    val bluetoothService: BluetoothService by inject()
    val storage: PulserStorage by inject()

    override suspend fun doWork(): Result {

        while (true) {

            bluetoothService.getData().let {

                while (it != null) {

                    if (it.available() > 0) {

                        coroutineScope {
                            var buf: String?
                            withContext(Dispatchers.IO) {

                                buf = it.read().toString()
    //                        buf = BufferedReader(InputStreamReader(it)).readLine()
                            }
                            launch(Dispatchers.Main) {
                                println(buf ?: "null")
                            }
                        }

                    }

                }
            }
        }
    }

}