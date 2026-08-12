package com.eime.devices

import retrofit2.http.GET;

interface DeviceService {

    /**
     * suspend es para que sea compatible con corrutinas
     */
    @GET(value=Constants.OBJECTS_PATH)
    suspend fun getAllObject(): List<Device>



}