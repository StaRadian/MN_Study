import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*

fun render(win: Long){
    glClearColor(0.0f,1.0f,0.0f,1.0f)
    glClear(GL_COLOR_BUFFER_BIT)

    glfwSwapBuffers(win)
}

fun main(){
    glfwInit()
    val window = glfwCreateWindow(640,480, "ToToKia", 0, 0)

    glfwMakeContextCurrent(window)
    GL.createCapabilities()


    while(!glfwWindowShouldClose(window)){
        render(window)
        glfwPollEvents()
    }

    glfwDestroyWindow(window)
    glfwTerminate()
}
