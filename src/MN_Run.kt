class MN_Run {
    fun Run() {
        //println("Hello Run!")
        val MN_win = MN_WIN()
        MN_win.Init()
        MN_win.Loop()
        MN_win.Destroy()
    }
}