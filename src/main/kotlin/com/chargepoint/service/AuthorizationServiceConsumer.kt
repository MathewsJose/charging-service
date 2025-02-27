package com.chargepoint.service

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.time.Duration
import com.chargepoint.model.ChargingSessionRequest
import com.chargepoint.model.CallbackPayload

@Service
class AuthorizationServiceConsumer(private val restTemplate: RestTemplate) {

    @RabbitListener(queues = ["chargingSessionQueue"])
    fun processChargingSession(request: ChargingSessionRequest) {
        // Simulate ACL check (in a real scenario, this would be an HTTP call to the internal service)
        val status = checkACL(request.stationId, request.driverToken)

        // Persist the decision (for debugging purposes)
        persistDecision(request.stationId, request.driverToken, status)

        // Send the result to the callback URL
        sendCallback(request.callbackUrl, CallbackPayload(request.stationId, request.driverToken, status))
    }

    private fun checkACL(stationId: String, driverToken: String): String {
        println("Checking ACL for stationId: $stationId and driverToken: $driverToken")
        Thread.sleep(Duration.ofSeconds(10).toMillis())
        return "allowed" // or "not_allowed", "unknown", "invalid"
    }

    private fun persistDecision(stationId: String, driverToken: String, status: String) {
        println("Persisting decision: stationId=$stationId, driverToken=$driverToken, status=$status")
    }

    private fun sendCallback(callbackUrl: String, payload: CallbackPayload) {
        restTemplate.postForEntity(callbackUrl, payload, String::class.java)
    }
}