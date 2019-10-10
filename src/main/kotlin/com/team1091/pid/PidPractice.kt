package com.team1091.pid

import com.team1091.pid.controller.HoldPositionController
import com.team1091.pid.controller.SpeedController
import processing.core.PApplet

fun main() {
    PApplet.main(arrayOf("com.team1091.pid.PIDPractice"))
}


class PIDPractice : PApplet() {

    val posWheel = Wheel(
        60f, 80f,
        30.0,
        1.0
    )
    val posMotor = Motor(posWheel)
    val posController = HoldPositionController(Math.toRadians(30.0))

    val speedWheel = Wheel(
        300f, 80f,
        30.0,
        1.0
    )
    val speedMotor = Motor(speedWheel)
    val speedController = SpeedController(10.0)

    override fun settings() {
        size(500, 500)
    }

    var lastTime = System.nanoTime()
    override fun draw() {
        val now = System.nanoTime();
        val dt = (now - lastTime).toDouble() / 1_000_000_000
        lastTime = now


        posMotor.set(posController.calcPower(posWheel))
        speedMotor.set(speedController.calcPower(speedWheel))

        // simulation
        posMotor.simulate(dt)
        speedMotor.simulate(dt)

        // display the wheel
        background(64)

        posWheel.render(this)
        speedWheel.render(this)

        posMotor.render(this)
//        speedMotor.render(this)

        stroke(0)
        text("Attempting to hold at ${posController.target}", 20f, 20f)
        text("Pos: ${posWheel.rotation}", 20f, 40f)
    }

    override fun mouseClicked() {
        val x = mouseX - 250
        val y = mouseY - 250
        posController.target = Math.atan2(y.toDouble(), x.toDouble())
    }
}


