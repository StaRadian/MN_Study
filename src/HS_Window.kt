import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFW.glfwSetErrorCallback




class HS_Window {
    private var window : Long = 0

    private var width : Int = 0
    private var height : Int = 0
    private var fullscreen : Boolean = true

    companion object {
        fun setCallbacks() {
            glfwSetErrorCallback(
                object : GLFWErrorCallback() {
                    override fun invoke(error: Int, description: Long) {
                        throw IllegalStateException(GLFWErrorCallback.getDescription(description))
                    }
                })
        }
    }

    init {
        setSize(640, 480)
        setFullscreen(false)
    }

    fun createWindow(title : String) {
        window = glfwCreateWindow(
            width,
            height,
            title,
            if(fullscreen) glfwGetPrimaryMonitor() else 0 ,
            0)

        if(window == 0L)
            throw IllegalStateException("Failed to create widow!")


        if(!fullscreen) {
            val vid = glfwGetVideoMode(glfwGetPrimaryMonitor())!!
            glfwSetWindowPos(
                window,
                (vid.width() - width) / 2,
                (vid.height() - height) / 2
            )
            glfwShowWindow(window)
        }

        glfwMakeContextCurrent(window)
    }

    fun shouldClose() : Boolean {
        return glfwWindowShouldClose(window) != false
    }

    fun swapBuffers() {
        glfwSwapBuffers(window)
    }

    fun setFullscreen(fullscreen : Boolean) {
        this.fullscreen = fullscreen
    }

    fun setSize(width : Int, height : Int) {
        this.width = width
        this.height = height
    }

    fun getWidth() : Int{ return width }

    fun getHeight() : Int { return height }

    fun isFullscreen() : Boolean { return fullscreen }

    fun getWindow() : Long {return window}

}