import java.io.File
import java.nio.file.Path

class Day1 {
    fun main(args: Array<String>) {
        run(Path.of("src/main/resources/day1/input.txt"))
    }

    fun run(input: Path) {
        val elves: MutableList<Elf> = mutableListOf()
        File(input.toString()).useLines { lines ->

            var elf = Elf()
            for (line in lines) {

                if (line.isNotEmpty()) {
                    elf.addCalories(line.toInt())
                } else {
                    elves.add(elf)
                    elf = Elf()
                }
            }
        }

        elves.sortByDescending { it.getAllCalories() }

        println(elves.take(3).sumOf { it.getAllCalories() });
    }

    class Elf {
        private val caloriesList: MutableList<Int> = mutableListOf()

        fun getAllCalories() = caloriesList.sum()
        fun addCalories(calories: Int) = caloriesList.add(calories)
        override fun toString(): String {
            return "Elf(totalCalories=${getAllCalories()})"
        }
    }
}