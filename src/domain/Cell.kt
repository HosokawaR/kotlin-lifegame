package domain

enum class LivingStatus {
    DEAD,
    ALIVE,
}

data class Location(val x: Int, val y: Int) {
    fun asIndex(world: World): Int {
        return y * world.width + x
    }

    fun isInside(world: World): Boolean {
        return 0 <= x && x < world.width && 0 <= y && y < world.height
    }

    companion object {
        fun fromIndex(index: Int, world: World): Location {
            return Location(index % world.width, index / world.width)
        }
    }
}

data class Cell(val id: String, val location: Location, val status: LivingStatus) {
    fun isAlive(): Boolean {
        return status == LivingStatus.ALIVE
    }

    fun isDead(): Boolean {
        return status == LivingStatus.DEAD
    }

    fun nextLivingStatus(judgement: JudgementResult): LivingStatus {
        return when(judgement) {
            JudgementResult.REPRODUCTION -> LivingStatus.ALIVE
            JudgementResult.SURVIVAL -> LivingStatus.ALIVE
            JudgementResult.OVER_POPULATION -> LivingStatus.DEAD
            JudgementResult.UNDER_POPULATION -> LivingStatus.DEAD
        }
    }
}
