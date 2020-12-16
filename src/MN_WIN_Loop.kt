import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11

abstract class MN_WIN_Loop : MN_WIN_Base () {
    protected fun Loop_Loop() {
        //println("Hello Loop")
        GL.createCapabilities();
        GL11.glClearColor(0.0f, 1.0f, 0.0f, 0.0f); //배경색 설정
        while(!GLFW.glfwWindowShouldClose(mn_window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
            GLFW.glfwSwapBuffers(mn_window)

            GLFW.glfwPollEvents()
        }
    }
}