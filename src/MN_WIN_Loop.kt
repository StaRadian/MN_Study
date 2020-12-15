import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11

class MN_WIN_Loop (window: Long) {
    val win  = window
    fun Loop() {
        println("MN_Loop")
        GL.createCapabilities()
        GL11.glClearColor(0.0f, 1.0f, 0.0f, 0.0f)

        while(!GLFW.glfwWindowShouldClose(win)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
            GLFW.glfwSwapBuffers(win)
            GLFW.glfwPollEvents()
        }
    }
}