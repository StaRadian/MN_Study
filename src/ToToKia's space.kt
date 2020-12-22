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
    val window = glfwCreateWindow(640,640, "ToToKia many colors", 0, 0)

    glfwShowWindow(window)
    glfwMakeContextCurrent(window)
    GL.createCapabilities()

    while(!glfwWindowShouldClose(window)){
        glfwPollEvents()
        glClear(GL_COLOR_BUFFER_BIT)
        glBegin(GL_POLYGON)

            glColor4f(1.0f,0.0f,0.0f,1.0f)
            glVertex2f(0.8f,0.8f)

            glColor4f(1.0f,1.0f,1.0f,1.0f)
            glVertex2f(0.3f,0.8f)

            glColor4f(0.0f,0.0f,1.0f,1.0f)
            glVertex2f(0.3f,0.6f)

            glColor4f(0.0f,1.0f,0.0f,1.0f)
            glVertex2f(0.4f,0.6f)

            glColor4f(1.0f,1.0f,1.0f,1.0f)
            glVertex2f(0.6f,0.4f)

            glColor4f(1.0f,1.0f,1.0f,1.0f)
            glVertex2f(0.6f,0.3f)

            glColor4f(0.0f,1.0f,1.0f,1.0f)
            glVertex2f(0.8f,0.3f)

        glEnd()

        glfwSwapBuffers(window)
    }

    glfwTerminate()
}

fun MoveWithWASD(){
    glfwInit()
    val window = glfwCreateWindow(640,640, "ToToKia move", 0, 0)

    glfwShowWindow(window)
    glfwMakeContextCurrent(window)
    GL.createCapabilities()

    var ymove : Float = 0.0f
    var xmove : Float = 0.0f

    while(!glfwWindowShouldClose(window)){
        if(glfwGetKey(window,GLFW_KEY_W) == GL_TRUE) { ymove += 0.01f }
        if(glfwGetKey(window,GLFW_KEY_S) == GL_TRUE) { ymove -= 0.01f }
        if(glfwGetKey(window,GLFW_KEY_A) == GL_TRUE) { xmove -= 0.01f }
        if(glfwGetKey(window,GLFW_KEY_D) == GL_TRUE) { xmove += 0.01f }

        glfwPollEvents()
        glClear(GL_COLOR_BUFFER_BIT)

        glBegin(GL_QUADS)

            glColor4f(1.0f,0.0f,0.0f,1.0f)
            glVertex2f(0.4f+xmove,0.4f+ymove)

            glColor4f(1.0f,1.0f,1.0f,1.0f)
            glVertex2f(0.4f+xmove,-0.4f+ymove)

            glColor4f(0.0f,0.0f,1.0f,1.0f)
            glVertex2f(-0.4f+xmove,-0.4f+ymove)

            glColor4f(0.0f,1.0f,0.0f,1.0f)
            glVertex2f(-0.4f+xmove,0.4f+ymove)

        glEnd()

        glfwSwapBuffers(window)

    }

    glfwTerminate()
}

fun ClickColorChange(){
    glfwInit()
    val window = glfwCreateWindow(640,640, "ToToKia click", 0, 0)

    glfwShowWindow(window)
    glfwMakeContextCurrent(window)
    GL.createCapabilities()

    var color1: Float = 1.0f
    var color2: Float = 0.0f
    var ox: Float = 0.4f
    var oy: Float = 0.4f

    while(!glfwWindowShouldClose(window)){
        if(glfwGetMouseButton(window,0) == GL_TRUE) {
            if((color1 == 1.0f) && (color2 == 0.0f)){
                color1 = 0.0f
                color2 = 1.0f
            }
            else{
                color1 = 1.0f
                color2 = 0.0f
            }
        }

        if(glfwGetMouseButton(window,1) == GL_TRUE) {
            if((ox == 0.4f) && (oy == 0.4f)){
                ox += 0.4f
                oy += 0.4f
            }
            else{
                ox -= 0.4f
                oy -= 0.4f
            }
        }

        glfwPollEvents()
        glClear(GL_COLOR_BUFFER_BIT)
        glBegin(GL_POLYGON)

            glColor4f(color1,0.0f,color2,1.0f)
            glVertex2f(ox,oy)

            glVertex2f(ox,-oy)

            glVertex2f(-ox,-oy)

            glVertex2f(-ox,oy)

        glEnd()

        glfwSwapBuffers(window)
    }

    glfwTerminate()
}
fun main(){
    println("---<예시선택>---")
    println("1.색을 띈 창 띄우기")
    println("2.그라데이션도형 창 띄우기")
    println("3.WASD이동 구현")
    println("4.좌클릭 색변경, 우클릭 확대")
    val reader = Scanner(System.`in`)
    print("번호입력:")

    when(reader.nextInt()){
        1 -> PopUpWithOneColor()
        2 -> PopUpWithMultipleColors()
        3 -> MoveWithWASD()
        4 -> ClickColorChange()
    }
}
