import org.lwjgl.glfw.Callbacks
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFWVidMode
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.MemoryUtil.NULL

open class MN_WIN_Base {
    var mn_window :Long = NULL

    fun Init(): Long { //GLFW 초기화 함수
        //println("Hello Init")
        GLFWErrorCallback.createPrint(System.err).set()

        if (!GLFW.glfwInit()) //GLFW 초기화
            throw IllegalStateException("GLFW 초기화 오류")

        GLFW.glfwDefaultWindowHints(); //기본 Hints로 초기화

        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE) // 처음 창 보임, false
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE) // 창 크기 조절 가능, false

        val vidmode : GLFWVidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor())!!

        mn_window = GLFW.glfwCreateWindow(  //window 크기, 제목, 모드 설정
            vidmode.width() / 3,
            vidmode.height() / 3,
            "MN Window!",
            MemoryUtil.NULL, MemoryUtil.NULL
        )

        if(mn_window == MemoryUtil.NULL)   //hs_window error
            throw RuntimeException("GLFW window 생성에 실패함")

        GLFW.glfwMakeContextCurrent(mn_window)
        GLFW.glfwSwapInterval(1) //Enable v-sync (60fps)
        GLFW.glfwShowWindow(mn_window)

        return mn_window
    }


    fun Destroy (){
        //println("Hello Destroy")
        Callbacks.glfwFreeCallbacks(mn_window)
        GLFW.glfwDestroyWindow(mn_window)

        GLFW.glfwTerminate()
        GLFW.glfwSetErrorCallback(null)!!.free()
    }
}