import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL15.*
import java.nio.FloatBuffer

class HS_Model (vertices : FloatArray, tex_coords : FloatArray) {
    private var draw_count : Int = 0
    private var v_id : Int = 0
    private var t_id : Int = 0

    init{
        draw_count =vertices.size / 3

        v_id = glGenBuffers()
        println(v_id)
        glBindBuffer(GL_ARRAY_BUFFER, v_id)
        glBufferData(GL_ARRAY_BUFFER, createBuffer(vertices) , GL_STATIC_DRAW)

        t_id = glGenBuffers()
        println(t_id)
        glBindBuffer(GL_ARRAY_BUFFER, t_id)
        glBufferData(GL_ARRAY_BUFFER, createBuffer(tex_coords) , GL_STATIC_DRAW)

        glBindBuffer(GL_ARRAY_BUFFER, 0)
    }

    fun render() {
        glEnableClientState(GL_VERTEX_ARRAY)
        glEnableClientState(GL_TEXTURE_COORD_ARRAY)

        glBindBuffer(GL_ARRAY_BUFFER, v_id)
        GL11.glVertexPointer(3, GL_FLOAT,0,0)

        glBindBuffer(GL_ARRAY_BUFFER, t_id)
        GL11.glTexCoordPointer(2, GL_FLOAT, 0,0)

        glDrawArrays(GL_TRIANGLES, 0, draw_count)

        glBindBuffer(GL_ARRAY_BUFFER, v_id)

        glDisableClientState(GL_VERTEX_ARRAY)
        glDisableClientState(GL_TEXTURE_COORD_ARRAY)
    }

    private fun createBuffer (data : FloatArray) : FloatBuffer {
        val buffer = BufferUtils.createFloatBuffer(data.size)
        buffer.put(data)
        buffer.flip()
        return buffer
    }

}