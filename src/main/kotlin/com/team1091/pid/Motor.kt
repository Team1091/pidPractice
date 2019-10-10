package com.team1091.pid

import kotlin.math.cos
import kotlin.math.sin

class Motor(
    val wheel: Wheel,
    val power: Double = 10.0,
    var percentage: Double = 0.0
) {

    fun set(percentage: Double) {
        this.percentage = Math.max(-1.0, Math.min(percentage, 1.0))
    }

    fun getTorque() = percentage * power +
            (wheel.angularVelocityW * -0.1) // calculate friction on motor - this prevents us from going to infinite speed

    fun simulate(dt: Double) {

        // this applies force to the wheel
        wheel.angularVelocityW += (getTorque() / wheel.momentOfInertiaI) * dt

        // this spins the wheel
        wheel.rotation += wheel.angularVelocityW * dt
    }


    fun render(pidPractice: PIDPractice) {
        with(pidPractice) {
            // Target line
            stroke(255f, 0f, 0f, 255f)
            line(
                wheel.x,
                wheel.y,
                (wheel.x + cos(posController.target) * wheel.radius * 1.2).toFloat(),
                (wheel.y + sin(posController.target) * wheel.radius * 1.2).toFloat()
            )
        }
    }
}