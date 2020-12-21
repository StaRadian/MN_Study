import java.lang.System.nanoTime

class Timer {
    companion object{
        fun getTime() : Double{
            return nanoTime().toDouble() / 1000000000.0
        }
    }
}