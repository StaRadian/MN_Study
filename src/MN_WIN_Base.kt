import org.lwjgl.glfw.Callbacks
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFWVidMode
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.MemoryUtil.NULL
import java.nio.IntBuffer

abstract class MN_WIN_Base {
    var mn_window :Long = NULL

    protected fun Base_Init() { //GLFW 초기화 함수
        //println("Hello Init")
        GLFWErrorCallback.createPrint(System.err).set()

        if (!GLFW.glfwInit()) //GLFW 초기화
            throw IllegalStateException("GLFW 초기화 오류")

        GLFW.glfwDefaultWindowHints(); //기본 Hints로 초기화
    }

    protected fun Base_Show_Init() {
        if(mn_window == MemoryUtil.NULL)   //hs_window error
            throw RuntimeException("GLFW window 생성에 실패함")
        GLFW.glfwMakeContextCurrent(mn_window)
        GLFW.glfwSwapInterval(1) //Enable v-sync (60fps)
        GLFW.glfwShowWindow(mn_window)
    }


    protected fun Base_Destroy (){
        //println("Hello Destroy")
        Callbacks.glfwFreeCallbacks(mn_window)
        GLFW.glfwDestroyWindow(mn_window)

        GLFW.glfwTerminate()
        GLFW.glfwSetErrorCallback(null)!!.free()
    }
}