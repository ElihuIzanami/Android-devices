package com.eime.devices

import retrofit2.http.GET;

interface DeviceService {

    /**
     * suspend es para que sea compatible con corrutinas
     * Para traer una lista de objetos
     */
    @GET(value=Constants.OBJECTS_PATH)
    suspend fun getAllDevices(): List<Device>

    /**
     * Para traer solo un objeto
     */
    @GET(Constants.OBJECTS_PATH + Constants.OBJECT_PATH)
    suspend fun getDevice(): Device

}