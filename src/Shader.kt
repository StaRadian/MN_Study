import org.joml.Matrix4f
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL20.*
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class Shader(filename : String) {
    private var program : Int = 0
    private var vs : Int = 0
    private var fs : Int = 0
    init{
        println("Hello Shader")
        program = glCreateProgram()

        vs = glCreateShader(GL_VERTEX_SHADER)
        glShaderSource(vs, readFile(filename + ".vs"))
        glCompileShader(vs)
        if(glGetShaderi(vs, GL_COMPILE_STATUS) != 1) {
            throw IllegalStateException(glGetShaderInfoLog(vs))
        }

        fs = glCreateShader(GL_FRAGMENT_SHADER)
        glShaderSource(fs, readFile(filename + ".fs"))
        glCompileShader(fs)
        if(glGetShaderi(fs, GL_COMPILE_STATUS) != 1) {
            throw IllegalStateException(glGetShaderInfoLog(fs))
        }

        glAttachShader(program, vs)
        glAttachShader(program, fs)

        glBindAttribLocation(program, 0, "vertices")
        glBindAttribLocation(program, 1, "textures")

        glLinkProgram(program)
        if(glGetProgrami(program, GL_LINK_STATUS) != 1) {
            throw IllegalStateException(glGetShaderInfoLog(program))
        }

        glValidateProgram(program)
        if(glGetProgrami(program, GL_VALIDATE_STATUS) != 1) {
            throw IllegalStateException(glGetShaderInfoLog(program))
        }
    }

    fun setUniform(name: String, value: Int) {
        val location = glGetUniformLocation(program, name)
        if(location != -1)
            glUniform1i (location, value)
    }

    fun setUniform(name: String, value: Matrix4f) {
        val location = glGetUniformLocation(program, name)
        val buffer = BufferUtils.createFloatBuffer(16)
        value.get(buffer)
        if(location != -1)
            glUniformMatrix4fv(location, false, buffer)
    }

    fun bind() {
        glUseProgram(program)
    }

    private fun readFile(filename: String) : String {
        val string  = StringBuilder()

        runCatching {
            val br = BufferedReader(FileReader(File("./shaders/" + filename)))
            var line = br.readLine()
            while(line != null){
                string.append(line)
                string.append("\n")
                line = br.readLine()
            }
            br.close()
        }.onFailure {

        }
        return string.toString()
    }
}