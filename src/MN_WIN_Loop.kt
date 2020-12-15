import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL11

open class MN_WIN_Loop : MN_WIN_Base () {
    fun Loop() {
        GL11.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
        while(!GLFW.glfwWindowShouldClose(mn_window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
            GLFW.glfwSwapBuffers(mn_window)

            GLFW.glfwPollEvents()
        }
    }
}