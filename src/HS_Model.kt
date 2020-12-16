import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL15.*
import java.nio.FloatBuffer

class HS_Model (vertices : FloatArray, tex_coords : FloatArray, indices : IntArray) {
    private var draw_count : Int = 0
    private var v_id : Int = 0
    private var t_id : Int = 0

    private var i_id : Int = 0

    init{
        draw_count = indices.size

        v_id = glGenBuffers()
        println(v_id)
        glBindBuffer(GL_ARRAY_BUFFER, v_id)
        glBufferData(GL_ARRAY_BUFFER, createBuffer(vertices) , GL_STATIC_DRAW)

        t_id = glGenBuffers()
        println(t_id)
        glBindBuffer(GL_ARRAY_BUFFER, t_id)
        glBufferData(GL_ARRAY_BUFFER, createBuffer(tex_coords) , GL_STATIC_DRAW)

        i_id = glGenBuffers()
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id)

        val buffer = BufferUtils.createIntBuffer(indices.size)
        buffer.put(indices)
        buffer.flip()

        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW)

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0)
        glBindBuffer(GL_ARRAY_BUFFER, 0)
    }

    fun render() {
        glEnableClientState(GL_VERTEX_ARRAY)
        glEnableClientState(GL_TEXTURE_COORD_ARRAY)

        glBindBuffer(GL_ARRAY_BUFFER, v_id)
        GL11.glVertexPointer(3, GL_FLOAT,0,0)

        glBindBuffer(GL_ARRAY_BUFFER, t_id)
        GL11.glTexCoordPointer(2, GL_FLOAT, 0,0)

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id)
        GL11.glDrawElements(GL_TRIANGLES, draw_count, GL_UNSIGNED_INT, 0)

        glDrawArrays(GL_TRIANGLES, 0, draw_count)

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0)
        glBindBuffer(GL_ARRAY_BUFFER, 0)

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