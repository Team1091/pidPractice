package com.team1091.pid.controller

import com.team1091.pid.Wheel

// Attempts to maintain a speed
// If too slow, full power.  If too fast, full reverse
class GunItController(val target: Double) : Controller {
    override fun calcPower(wheel: Wheel): Double {
        return if (target > wheel.angularVelocityW)
            1.0
        else -1.0
    }
}