import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*

fun main(){
    GLFWErrorCallback.createPrint()
    if(!glfwInit())
        throw IllegalStateException("glfwInit() error!")

    var win : Long = glfwCreateWindow(640, 480, "Hello", 0,0)

    glfwShowWindow(win)

    glfwMakeContextCurrent(win)

    GL.createCapabilities()

    var x : Float = 0f;


    while (!glfwWindowShouldClose(win)){
        if(glfwGetKey(win, GLFW_KEY_A) == GL_TRUE) {
            x += 0.001f
        }

        glfwPollEvents()

        glClear(GL_COLOR_BUFFER_BIT)

        glBegin(GL_QUADS)
        glColor4f(1f,0f,0f, 0f)
        glVertex2f(-0.5f+x, 0.5f)
        glColor4f(0f,1f,0f, 0f)
        glVertex2f(0.5f+x, 0.5f)
        glColor4f(0f,0f,1f, 0f)
        glVertex2f(0.5f+x, -0.5f)
        glColor4f(1f,1f,1f, 0f)
        glVertex2f(-0.5f+x, -0.5f)
        glEnd()

        glfwSwapBuffers(win)
    }

    glfwTerminate()
}