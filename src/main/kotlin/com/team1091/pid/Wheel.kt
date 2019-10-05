package com.team1091.pid

class Wheel(
    val diameter: Double,
    val momentOfInertiaI: Double,
    var rotation: Double = 0.0,
    var angularVelocityW: Double = 0.0
) {
    override fun toString(): String = "$rotation $angularVelocityW"
}