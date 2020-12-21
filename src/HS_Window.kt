import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWVidMode

class HS_Window {
    private var window : Long = 0

    private var width : Int = 0
    private var height : Int = 0

    init {
        setSize(604, 480)
    }

    fun createWindow(title : String) {
        window = glfwCreateWindow(width, height, title, 0, 0)

        if(window == 0L)
            throw IllegalStateException("Failed to create widow!")

        val vid = glfwGetVideoMode(glfwGetPrimaryMonitor())!!
        glfwSetWindowPos(window,
            (vid.width() - width)/ 2,
            (vid.height() - height)/2)

        glfwShowWindow(window)

        glfwMakeContextCurrent(window)
    }

    fun shouldClose() : Boolean {
        return glfwWindowShouldClose(window) != false
    }

    fun swapBuffers() {
        glfwSwapBuffers(window)
    }

    fun setSize(width : Int, height : Int) {
        this.width = width
        this.height = height
    }

    fun getWidth() : Int{
        return width
    }

    fun getHeight() : Int {
        return height
    }

}