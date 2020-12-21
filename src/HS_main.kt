import org.joml.Matrix4f
import org.joml.Vector3f
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*

fun main(){
    GLFWErrorCallback.createPrint()
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
    if(!glfwInit())
        throw IllegalStateException("glfwInit() error!")

    var win : Long = glfwCreateWindow(640, 480, "Hello", 0,0)

    glfwShowWindow(win)

    glfwMakeContextCurrent(win)

    GL.createCapabilities()

    val camera = HS_Camera(640, 480)

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
    val shader = Shader("HS_shader")
    val tex = HS_texture("./res/awesomeface.png")
    val scale = Matrix4f()
        .translate(Vector3f(100f, 0f, 0f))
        .scale(256f)
    var target = Matrix4f()

    camera.setPosition(Vector3f(-100f, 0f, 0f))

    val frame_cap : Double = 1.0/60.0

    var frame_time : Double = 0.0
    var frames = 0

    var time = HS_Timer.getTime()
    var unprocessed : Double = 0.0


    while (!glfwWindowShouldClose(win)){
        var can_render =false

        var time_2 = HS_Timer.getTime()
        var passed = time_2 - time
        unprocessed += passed
        frame_time += passed

        time = time_2

        while(unprocessed >= frame_cap) {
            unprocessed -= frame_cap
            can_render = true

            target = scale
            if(glfwGetKey(win, GLFW_KEY_A) == GL_TRUE) {
                glfwSetWindowShouldClose(win, true)
            }

            glfwPollEvents()
            if(frame_time >= 1.0) {
                frame_time = 0.0
                println("FPS: $frames")
                frames = 0
            }
        }

        if(can_render) {
            glClear(GL_COLOR_BUFFER_BIT)

            shader.bind()
            shader.setUniform("sampler",0)
            shader.setUniform("projection",camera.getprojection().mul(target))
            model.render()
            tex.bind(0)

            glfwSwapBuffers(win)
            frames++
        }
    }

    glfwTerminate()
}
