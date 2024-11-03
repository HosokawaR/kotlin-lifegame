package ui

import application.SimulateUsecase
import domain.Generation
import domain.LivingStatus
import domain.World

class Gameboard {
    val simurator = SimulateUsecase()

    fun run() {
        simurator.run { world, generation ->
            clear()
            print(world, generation)
        }
    }

    private fun print(world: World, generation: Generation) {
        val output = generation.cells.map {
            when (it.status) {
                LivingStatus.ALIVE -> "■"
                LivingStatus.DEAD -> "□"
            }
        }.chunked(world.width).joinToString(separator = "\n") { it.joinToString(separator = "") }

        println(output)
    }

    private fun clear() {
        val os = System.getProperty("os.name")
        if (os.contains("win")) {
            Runtime.getRuntime().exec("cmd /c cls")
        } else {
            Runtime.getRuntime().exec("clear")
        }
    }
}

