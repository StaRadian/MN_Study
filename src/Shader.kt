import org.lwjgl.opengl.GL20.*
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class Shader(filename : String) {
    private var program : Int = 0
    private var vs : Int = 0
    private var fs : Int = 0
    init{
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

        glLinkProgram(program)
        if(glGetProgrami(program, GL_LINK_STATUS) != 1) {
            throw IllegalStateException(glGetShaderInfoLog(program))
        }

        glValidateProgram(program)
        if(glGetProgrami(program, GL_VALIDATE_STATUS) != 1) {
            throw IllegalStateException(glGetShaderInfoLog(program))
        }
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