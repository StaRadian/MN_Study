package hs_io

import java.lang.System.nanoTime

class HS_Timer {
    companion object{
        fun getTime() : Double{
            return nanoTime().toDouble() / 1000000000.0
        }
    }
}