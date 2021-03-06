package hs_render

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL13.*
import java.io.File
import javax.imageio.ImageIO
import java.awt.image.BufferedImage


class HS_texture(filename : String)  {
    private var id : Int = 0

    init {
        kotlin.runCatching {
            val bi: BufferedImage = ImageIO.read(File(filename))
            val width = bi.width
            val height = bi.height

            val pixels_raw = bi.getRGB(0,0, width, height, null, 0, width)
            val pixels = BufferUtils.createByteBuffer(width * height * 4)

            for(i : Int in 0..width-1) {
                for(j : Int in 0..height-1){
                    val pixel = pixels_raw[i*height + j]
                    pixels.put((pixel.shr(16) and 0xFF).toByte())
                    pixels.put((pixel.shr(8) and 0xFF).toByte())
                    pixels.put((pixel and 0xFF).toByte())
                    pixels.put((pixel.shr(24) and 0xFF).toByte())
                }
            }
            pixels.flip()

            id = glGenTextures()

            glBindTexture(GL_TEXTURE_2D, id)

            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST.toFloat())
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST.toFloat())

            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels)

        } .onFailure {
            println("fail")
        }
    }

    fun bind(sampler : Int) {
        if((sampler >= 0) && (sampler <=31)) {
            glActiveTexture(GL_TEXTURE0 + sampler)
            glBindTexture(GL_TEXTURE_2D, id)
        }
    }

}