package hs_world

class Tile (id : Byte, texture : String) {

    var tiles = arrayOfNulls<Tile>(32)
    var test_tile = Tile(0.toByte(), "test")

    private var id : Byte
    private var texture : String

    init {
        this.id = id
        this.texture = texture
        if(tiles[id.toInt()] != null) {
            throw IllegalStateException("Tiles at: [" + id + "] is already being used!")
        }
        tiles[id.toInt()] = this
    }

    fun getId() : Byte {
        return id
    }

    fun getTexture() : String {
        return texture
    }
}