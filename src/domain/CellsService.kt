package domain

enum class JudgementResult {
    SURVIVAL,
    UNDER_POPULATION,
    OVER_POPULATION,
    REPRODUCTION,
}

class CellJudgementService(val generation: Generation, val world: World) {
    fun judge(cell: Cell): JudgementResult {
        val count = countAroundLiving(cell)
        if (0 <= count && count <= 1) {
            return JudgementResult.UNDER_POPULATION
        } else if (2 <= count && count <= 3) {
            if (cell.isAlive()) return JudgementResult.SURVIVAL
            else JudgementResult.REPRODUCTION
        } else {
            return JudgementResult.OVER_POPULATION
        }
        // TODO: ダサいから治す
        return JudgementResult.SURVIVAL
    }

    private fun countAroundLiving(cell: Cell): Int {
        var count = 0

        for (dy in -1..1) {
            for (dx in -1..1) {
                val location = Location(x = cell.location.x + dx, y = cell.location.y + dy)
                if (!location.isInside(world = world)) continue
                generation.cells
                val c = generation.findCell(location = location, world = world)
                if (c.isAlive()) count++;
            }
        }

        return count
    }
}
