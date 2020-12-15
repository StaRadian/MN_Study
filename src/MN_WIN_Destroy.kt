import org.lwjgl.glfw.Callbacks
import org.lwjgl.glfw.GLFW

class MN_WIN_Destroy(window: Long) {
    val win = window
    fun Destroy (){
        println("MN_Destroy")
        Callbacks.glfwFreeCallbacks(win)
        GLFW.glfwDestroyWindow(win)

        GLFW.glfwTerminate()
        GLFW.glfwSetErrorCallback(null)!!.free()
    }
}