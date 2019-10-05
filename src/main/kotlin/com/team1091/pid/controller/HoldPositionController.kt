package com.team1091.pid.controller

import com.team1091.pid.Wheel

// Attempts to go to and hold a position
class HoldPositionController(var target: Double = 0.0) : Controller {

    //https://frc-pdr.readthedocs.io/en/latest/control/pid_control.html
    override fun calcPower(wheel: Wheel): Double {

        // TODO: we should convert this into a PID loop
        return if (target > wheel.rotation)
            1.0
        else -1.0
    }

}