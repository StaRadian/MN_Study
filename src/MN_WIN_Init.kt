import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFWVidMode
import org.lwjgl.system.MemoryUtil

class MN_WIN_Init {
    fun Init(): Long { //GLFW 초기화 함수
        println("MN_Init")
        GLFWErrorCallback.createPrint(System.err).set()

        if (!GLFW.glfwInit()) //GLFW 초기화
            throw IllegalStateException("GLFW 초기화 오류")

        GLFW.glfwDefaultWindowHints(); //기본 Hints로 초기화

        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE) // 처음 창 보임, false
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE) // 창 크기 조절 가능, false

        val vidmode : GLFWVidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor())!!

        val mn_window = GLFW.glfwCreateWindow(
            vidmode.width() / 3,
            vidmode.height() / 3,
            "MN Window!",
            MemoryUtil.NULL, MemoryUtil.NULL
        )

        if(mn_window == MemoryUtil.NULL)   //hs_window error
            throw RuntimeException("GLFW window 생성에 실패함")

        var ESCAPE_KeyCallback = fun(window :Long, key :Int, scancode: Int, action: Int, mod: Int) {
            if((key == GLFW.GLFW_KEY_ESCAPE) && (action == GLFW.GLFW_RELEASE)) //esc 버튼을 눌렀을때 꺼짐
                GLFW.glfwSetWindowShouldClose(window, true)
        }

        GLFW.glfwSetKeyCallback(mn_window, ESCAPE_KeyCallback) //Callback 설정

        GLFW.glfwMakeContextCurrent(mn_window)
        GLFW.glfwSwapInterval(1) //Enable v-sync
        GLFW.glfwShowWindow(mn_window)
        return mn_window
    }
}