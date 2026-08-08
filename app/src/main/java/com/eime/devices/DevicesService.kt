package com.eime.devices

import retrofit2.http.GET;

interface DevicesService {

    @GET()
    suspend fun getAllObject(): List<Device>



}