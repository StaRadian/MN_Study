import org.joml.Matrix4f
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*

fun main(){
    println("Hello")
    GLFWErrorCallback.createPrint()
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
    if(!glfwInit())
        throw IllegalStateException("glfwInit() error!")

    var win : Long = glfwCreateWindow(640, 480, "Hello", 0,0)

    glfwShowWindow(win)

    glfwMakeContextCurrent(win)

    GL.createCapabilities()

    glEnable(GL_TEXTURE_2D)

    val vertices = floatArrayOf(
        -0.5f , 0.5f , 0f ,     //Top LEFT      0
        0.5f, 0.5f, 0f,         //Top RIGHT     1
        0.5f, -0.5f, 0f,        //BOTTOM RIGHT  2
        -0.5f, -0.5f, 0f,       //BOTTOM LEFT   3

    )

    val texture = floatArrayOf(
        0f,0f,
        1f,0f,
        1f,1f,
        0f,1f
    )

    val indices = intArrayOf(
        0,1,2,
        2,3,0
    )

    val model = HS_Model(vertices, texture, indices)
    val shader = Shader("shader")
    val tex = HS_texture("./res/awesomeface.png")

    val projection = Matrix4f().ortho2D(-640/2f, 640/2f, -480/2f, 480/2f)
    val scale = Matrix4f().scale(128f)
    val target = Matrix4f()

    projection.mul(scale, target)

    while (!glfwWindowShouldClose(win)){
        if(glfwGetKey(win, GLFW_KEY_A) == GL_TRUE) {
            glfwSetWindowShouldClose(win, true)
        }

        glfwPollEvents()

        glClear(GL_COLOR_BUFFER_BIT)


        shader.bind()
        shader.setUniform("sampler",0)
        shader.setUniform("projection",target)

        tex.bind(0)
        model.render()

        glfwSwapBuffers(win)
    }

    glfwTerminate()
}
