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

    glEnable(GL_TEXTURE_2D)

    val vertices = floatArrayOf(
        -0.5f , 0.5f , 0f ,    //Top LEFT
        0.5f, 0.5f, 0f,         //Top RIGHT
        0.5f, -0.5f, 0f,        //BOTTOM RIGHT

        0.5f, -0.5f, 0f,        //BOTTOM RIGHT
        -0.5f, -0.5f, 0f,         //Top RIGHT
        -0.5f , 0.5f , 0f ,    //Top LEFT

    )

    val texture = floatArrayOf(
        0f,0f,
        1f,0f,
        1f,1f,

        1f,1f,
        0f,1f,
        0f,0f
    )

    val model = HS_Model(vertices, texture)

    val tex = HS_texture("./res/awesomeface.png")


    while (!glfwWindowShouldClose(win)){
        if(glfwGetKey(win, GLFW_KEY_A) == GL_TRUE) {
            glfwSetWindowShouldClose(win, true)
        }

        glfwPollEvents()

        glClear(GL_COLOR_BUFFER_BIT)

        tex.bind()

        model.render()

        glfwSwapBuffers(win)
    }

    glfwTerminate()
}
