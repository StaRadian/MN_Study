import org.joml.Matrix4f
import org.joml.Vector3f

class HS_Camera(width : Int, height : Int) {
    private var position : Vector3f
    private var projection : Matrix4f

    init {
        position = Vector3f(0f,0f,0f)
        projection = Matrix4f().setOrtho2D(-width/2f, width/2f, -height/2f, height/2f )
    }

    fun setPosition(position : Vector3f) {
        this.position = position
    }

    fun addPosition(position : Vector3f) {
        this.position.add(position)
    }

    fun getPosition() : Vector3f {
        return position
    }

    fun getprojection() : Matrix4f {
        var target = Matrix4f()
        val pos = Matrix4f().setTranslation(position)

        target = projection.mul(pos, target)
        return target
    }
}