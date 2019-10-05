package com.team1091.pid

import com.team1091.pid.controller.HoldPositionController
import processing.core.PApplet
import processing.core.PConstants
import kotlin.math.cos
import kotlin.math.sin

fun main() {
    PApplet.main(arrayOf("com.team1091.pid.PIDPractice"))
}


class PIDPractice : PApplet() {

    val wheel = Wheel(
        30.0,
        1.0
    )
    val motor = Motor(wheel)
    val controller =
        HoldPositionController(Math.toRadians(30.0)) //com.team1091.pid.controller.GunItController(10.0)

    override fun settings() {
        size(500, 500)
    }

    var lastTime = System.nanoTime()
    override fun draw() {
        val now = System.nanoTime();
        val dt = (now - lastTime).toDouble() / 1_000_000_000
        lastTime = now

        // simulation
        motor.set(controller.calcPower(wheel))

        motor.simulate(dt)

        // display the wheel
        background(64)

        val centerX = 250f
        val centerY = 250f
        val wheelRadius = wheel.diameter.toFloat()

        ellipseMode(PConstants.RADIUS)
        ellipse(centerX, centerY, wheelRadius, wheelRadius)
        line(
            centerX,
            centerY,
            centerX + cos(wheel.rotation).toFloat() * wheelRadius,
            centerY + sin(wheel.rotation).toFloat() * wheelRadius
        )

        // Target line
        stroke(255f, 0f, 0f, 255f)
        line(
            centerX,
            centerY,
            centerX + cos(controller.target).toFloat() * wheelRadius * 1.2f,
            centerY + sin(controller.target).toFloat() * wheelRadius * 1.2f
        )

        stroke(0)
        text("Attempting to hold at ${controller.target}", 20f, 20f)
        text("Pos: ${wheel.rotation}", 20f, 40f)
    }

    override fun mouseClicked() {
        val x = mouseX - 250
        val y = mouseY - 250
        controller.target = Math.atan2(y.toDouble(), x.toDouble())
    }
}


