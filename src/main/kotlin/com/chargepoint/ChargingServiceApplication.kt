package com.chargepoint

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChargingServiceApplication

fun main(args: Array<String>) {
    runApplication<ChargingServiceApplication>(*args)
}