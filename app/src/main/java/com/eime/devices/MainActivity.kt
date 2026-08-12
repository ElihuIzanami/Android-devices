package com.eime.devices

import MainView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.eime.devices.ui.theme.DevicesTheme
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DevicesTheme {
                //Variable para ingresar al activity de los dispositivos
                var devices by remember {
                    mutableStateOf(listOf<Device>()) }
                //Mandao a llamar la funcion
                getDevices { result ->
                    devices = result
                }



                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainView(Modifier.padding(innerPadding),
                        devices = devices )

                }
            }
        }
    }

    //Funcion de Orden superior para trael del we services
    private fun getDevices(onResult:(List<Device>) -> Unit) {
        //Configuracion base de retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Creacion del servicio
        val service = retrofit.create(DeviceService::class.java)
        lifecycleScope.launch {
            //Ejecucion del servicio
            val devices = service.getAllObject()
            //Devolvemos lo del wb service
            onResult(devices)
        }
    }



}