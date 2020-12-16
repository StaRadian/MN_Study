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

    var color_red : Float = 1f;
    var color_blue : Float = 0f;


    while (!glfwWindowShouldClose(win)){
        if(glfwGetKey(win, GLFW_KEY_A) == GL_TRUE) { }

        glfwPollEvents()

        glClear(GL_COLOR_BUFFER_BIT)

        glBegin(GL_QUADS)
        glColor4f(color_red,0f,color_blue, 0f)
        glVertex2f(-0.5f, 0.5f)
        glVertex2f(0.5f, 0.5f)
        glVertex2f(0.5f, -0.5f)
        glVertex2f(-0.5f, -0.5f)
        glEnd()

        glfwSwapBuffers(win)
    }

    glfwTerminate()
}