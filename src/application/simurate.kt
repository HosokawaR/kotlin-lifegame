package application

import domain.Generation
import domain.World

const val INTERVAL = 1000 // ms

class SimulateUsecase {
    fun run(callback: (World, Generation) -> Unit) {
        val world = World(width = 20, height = 10)
        var generation = world.setup()

        while (true) {
            generation = generation.next(world)
            Thread.sleep(INTERVAL.toLong())
            callback(world, generation)
       }
    }
}