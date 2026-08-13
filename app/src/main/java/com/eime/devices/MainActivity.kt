package com.eime.devices

import MainView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
                var devices by remember { mutableStateOf(listOf<Device>()) }
                var isLoading by remember {  mutableStateOf(true) }
                //Mando a llamar la funcion que trae la lista de Objetos Device
                getDevices { result ->
                    if(result!=null) {
                        devices = result
                    }
                    isLoading = false
                }
                //Mando a llamar solo un Objeto de Device
                /*getDeviceOne { result ->
                    devices = result
                }*/

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        MainView(Modifier.padding(innerPadding),
                            devices = devices )

                        if(isLoading) {
                            CircularProgressIndicator()
                        }

                    }
                }
            }
        }
    }

    //Funcion de Orden superior para traer una lista de Objetos Device del wb-services
    fun getDevices(onResult:(List<Device>) -> Unit) {
        //Configuracion base de retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //Creacion del servicio
        val service = retrofit.create(DeviceService::class.java)
        var devices: List<Device>? = null
        lifecycleScope.launch {
            //Ejecucion del servicio
            devices = service.getAllDevices()
        }.invokeOnCompletion {
            //Devolvemos lo del wb service
            onResult(devices)
        }
    }

    //Funcion para solo traer un objeto Device del wb-service
    fun getDeviceOne(onResult:(List<Device>) -> Unit) {
        //Configuracion base de retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //Creacion del servicio
        val service = retrofit.create(DeviceService::class.java)
        lifecycleScope.launch {
            //Ejecucion del servicio
            val device = service.getDevice()
            //Devolvemos lo del wb service
            onResult(listOf(device))
        }
    }



}