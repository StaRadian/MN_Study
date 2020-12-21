import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import java.util.*

fun PopUpWithOneColor(){
    glfwInit()
    val window = glfwCreateWindow(640,480, "ToToKia one color", 0, 0)

    glfwMakeContextCurrent(window)
    GL.createCapabilities()


    while(!glfwWindowShouldClose(window)){
        glClearColor(0.0f,1.0f,0.0f,1.0f)
        glClear(GL_COLOR_BUFFER_BIT)

        glfwSwapBuffers(window)

        glfwPollEvents()
    }

    glfwDestroyWindow(window)
    glfwTerminate()
}

fun PopUpWithMultipleColors(){
    glfwInit()
    val window = glfwCreateWindow(640,480, "ToToKia many colors", 0, 0)

    glfwShowWindow(window)
    glfwMakeContextCurrent(window)
    GL.createCapabilities()

    while(!glfwWindowShouldClose(window)){
        glfwPollEvents()
        glClear(GL_COLOR_BUFFER_BIT)
        glBegin(GL_QUADS)

            glColor4f(1.0f,0.0f,0.0f,1.0f)
            glVertex2f(-0.5f,0.5f)

            glColor4f(0.0f,1.0f,0.0f,1.0f)
            glVertex2f(0.5f,0.5f)

            glColor4f(0.0f,0.0f,1.0f,1.0f)
            glVertex2f(0.5f,-0.5f)

            glColor4f(1.0f,1.0f,1.0f,1.0f)
            glVertex2f(-0.5f,-0.5f)
        glEnd()

        glfwSwapBuffers(window)
    }

    glfwTerminate()
}

fun main(){
    println("---<예시선택>---")
    println("1.색을 띈 창띄우기")
    println("2.그라데이션 창띄우기")
    val reader = Scanner(System.`in`)
    print("번호입력:")
    val num = reader.nextInt()

    when(num){
        1 -> PopUpWithOneColor()
        2 -> PopUpWithMultipleColors()
    }
}
