package com.team1091.pid

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
        wheel.angularVelocityW += (getTorque()/wheel.momentOfInertiaI) * dt

        // this spins the wheel
        wheel.rotation += wheel.angularVelocityW * dt
    }

}