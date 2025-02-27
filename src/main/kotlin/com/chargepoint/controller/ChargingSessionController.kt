package com.chargepoint.controller

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import jakarta.validation.Valid
import com.chargepoint.model.ChargingSessionRequest
import com.chargepoint.model.AcknowledgmentResponse

@RestController
class ChargingSessionController(private val rabbitTemplate: RabbitTemplate) {

    @PostMapping("/startChargingSession")
    fun startChargingSession(@Valid @RequestBody request: ChargingSessionRequest): ResponseEntity<AcknowledgmentResponse> {
        // Validate the request (Spring Validation will handle this)
        
        // println("Hello World")
        // Send the request to the queue
        rabbitTemplate.convertAndSend("chargingSessionQueue", request)

        // Return acknowledgment response
        return ResponseEntity.ok(AcknowledgmentResponse("accepted", "Request is being processed asynchronously. The result will be sent to the provided callback URL."))
    }
}

@RestController
class TestController {
    @PostMapping("/test")
    fun test(): String {
        return "Hello, World!"
    }
}