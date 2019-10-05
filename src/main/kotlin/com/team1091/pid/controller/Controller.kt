package com.team1091.pid.controller

import com.team1091.pid.Wheel

interface Controller {
    fun calcPower(wheel: Wheel): Double
}