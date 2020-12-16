import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*

fun main(){
    GLFWErrorCallback.createPrint()
    if(!glfwInit())
        throw IllegalStateException("glfwInit() error!")

    var win : Long = glfwCreateWindow(600, 600, "Hello", 0,0)

    glfwShowWindow(win)

    glfwMakeContextCurrent(win)

    GL.createCapabilities()

    glEnable(GL_TEXTURE_2D)

    val tex = HS_texture("./res/awesomeface.png")

    while (!glfwWindowShouldClose(win)){
        if(glfwGetKey(win, GLFW_KEY_A) == GL_TRUE) {
            glfwSetWindowShouldClose(win, true)
        }

        glfwPollEvents()

        glClear(GL_COLOR_BUFFER_BIT)

        tex.bind()

        glBegin(GL_QUADS)

        glTexCoord2f(0f,0f)
        glVertex2f(-0.5f, 0.5f)

        glTexCoord2f(1f,0f)
        glVertex2f(0.5f, 0.5f)

        glTexCoord2f(1f,1f)
        glVertex2f(0.5f, -0.5f)

        glTexCoord2f(0f,1f)
        glVertex2f(-0.5f, -0.5f)
        glEnd()

        glfwSwapBuffers(win)
    }

    glfwTerminate()
}
