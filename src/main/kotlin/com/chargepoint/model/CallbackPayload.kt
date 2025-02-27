package com.chargepoint.model

data class CallbackPayload(
    val stationId: String,
    val driverToken: String,
    val status: String // Possible values: allowed, not_allowed, unknown, invalid
)