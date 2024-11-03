package domain


data class World(val width: Int, val height: Int) {
    val size: Int
        get() = width * height

    fun setup(): Generation {
        return Generation.fillCellRandomly((this))
    }
}
