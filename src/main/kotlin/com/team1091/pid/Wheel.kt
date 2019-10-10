package com.team1091.pid

import processing.core.PConstants
import kotlin.math.cos
import kotlin.math.sin

class Wheel(
    val x: Float,
    val y: Float,
    val radius: Double,
    val momentOfInertiaI: Double,
    var rotation: Double = 0.0,
    var angularVelocityW: Double = 0.0
) {
    override fun toString(): String = "$rotation $angularVelocityW"


    fun render(pidPractice: PIDPractice) {

        with(pidPractice) {
            ellipseMode(PConstants.RADIUS)
            ellipse(x, y, radius.toFloat(), radius.toFloat())
            line(
                x,
                y,
                (x + cos(rotation) * radius).toFloat(),
                (y + sin(rotation) * radius).toFloat()
            )
        }

    }
}