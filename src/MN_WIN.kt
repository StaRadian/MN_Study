import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWVidMode
import org.lwjgl.opengl.GL11.glGetIntegerv
import org.lwjgl.opengl.GL20.GL_MAX_VERTEX_ATTRIBS
import org.lwjgl.system.MemoryUtil
import java.nio.IntBuffer

open class MN_WIN:MN_WIN_Loop() {

    fun Init() {

        Base_Init()
        mn_window_Init()
        Base_Show_Init()
    }

    fun Loop() {
        Loop_Loop()
    }

    fun Destroy() {
        Base_Destroy()
    }

    private fun mn_window_Init() {
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE) // 처음 창 보임, false
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE) // 창 크기 조절 가능, false

        val vidmode : GLFWVidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor())!!

        mn_window = GLFW.glfwCreateWindow(  //window 크기, 제목, 모드 설정
            vidmode.width() / 3,    // 화면 넓이
            vidmode.height() / 3,   // 화면 높이
            "MN Window!",   //창 제목
            MemoryUtil.NULL, MemoryUtil.NULL
        )
    }

    private fun Key_Init() {

    }
}