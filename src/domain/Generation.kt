package domain

import kotlin.random.Random

const val ALIVE_PROBABILITY = 0.2

data class Generation(val id: String, val cells: List<Cell>) {
    fun findCell(location: Location, world: World): Cell {
        return cells[location.asIndex(world)]
    }

    fun next(world: World): Generation {
        val service = CellJudgementService(generation = this, world = world)
        return Generation(id = this.id + 1, cells = this.cells.map {
            Cell(
                id = "xxx",
                location = it.location,
                status = it.nextLivingStatus(service.judge(cell = it))
            )
        })
    }

    companion object {
        fun fillCellRandomly(world: World): Generation {
            return Generation(
                id = "xxx",
                cells = (0..<world.size).map {
                    val status = if (Random.nextDouble() < ALIVE_PROBABILITY) LivingStatus.ALIVE else LivingStatus.DEAD
                    Cell(
                        id = "",
                        location = Location.fromIndex(it, world),
                        status = status
                    )
                }
            )
        }
    }
}
