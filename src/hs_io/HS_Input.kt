package hs_io

import org.lwjgl.glfw.GLFW.*

class HS_Input(window: Long) {
    private var window : Long = 0

    private lateinit var keys : BooleanArray

    init {
        this.window = window
        this.keys = BooleanArray(GLFW_KEY_LAST)
        for(i : Int in 32..GLFW_KEY_LAST - 1) {
            keys[i] = false
        }
    }

    fun isKeyDown (key : Int) : Boolean {
        return glfwGetKey(window, key) == 1
    }

    fun isKeyPressed(key : Int) : Boolean{
        return isKeyDown(key) && (!keys[key])
    }

    fun isKeyReleased(key : Int) : Boolean{
        return (!isKeyDown(key)) && keys[key]
    }

    fun isMouseButtonDown (button : Int) : Boolean {
        return glfwGetMouseButton(window, button) == 1
    }

    fun update() {
        for(i : Int in 32..GLFW_KEY_LAST - 1) {
            keys[i] = isKeyDown(i)
        }
    }

}