// package com.chargepoint.model

// import jakarta.validation.constraints.NotBlank

// data class ChargingSessionRequest(
//     @field:NotBlank(message = "Session ID cannot be empty")
//     val stationId: String,
    
//     val driverToken: String,
    
//     @field:NotBlank(message = "Callback URL cannot be empty")
//     val callbackUrl: String
// )
package com.chargepoint.model

import java.io.Serializable

data class ChargingSessionRequest(
    val stationId: String,
    val driverToken: String,
    val callbackUrl: String
) : Serializable