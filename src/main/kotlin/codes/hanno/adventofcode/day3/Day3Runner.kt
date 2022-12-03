import java.io.File
import java.nio.file.Path

fun main(args: Array<String>) {
    Day3Runner().run(Path.of("src/main/resources/day3/input.txt"))
}

class Day3Runner {

    fun run(input: Path) {
        File(input.toString()).useLines { lines ->
            val groups = mutableListOf<Group>()
            val rucksacks = mutableListOf<Rucksack>()

            lines.forEachIndexed { index, line ->
                rucksacks.add(Rucksack(line))

                if ((index + 1) % 3 == 0) {
                    groups.add(Group(rucksacks.toList()))
                    rucksacks.clear()
                }
            }

            val sumOfProrities = groups.map {
                it.determineBadgeItem().priority()
            }.toList().sum()

            println(sumOfProrities)
        }
    }

    data class Group(val rucksacks: List<Rucksack>) {
        fun determineBadgeItem(): Item {
            val itemsRucksack1 = rucksacks.get(0).getAllItems()
            val itemsRucksack2 = rucksacks.get(1).getAllItems()
            val itemsRucksack3 = rucksacks.get(2).getAllItems()


            return itemsRucksack1.intersect(itemsRucksack2).intersect(itemsRucksack3).first()
        }
    }

    class Rucksack(input: String) {
        val firstCompartment: MutableList<Item> = mutableListOf()
        val secondCompartment: MutableList<Item> = mutableListOf()

        init {
            val indexHalf = (input.length / 2 - 1)

            for (firstHalfChar in input.substring(IntRange(0, indexHalf)).toList())
                firstCompartment.add(Item(firstHalfChar))

            for (secondHalfChar in input.substring(IntRange(indexHalf + 1, input.length - 1)).toList())
                secondCompartment.add(Item(secondHalfChar))

            assert(firstCompartment.size == secondCompartment.size)
        }

        fun determineDuplicateItem(): Item {
            return firstCompartment.intersect(secondCompartment).first()
        }

        fun getAllItems(): Set<Item> {
            return firstCompartment.union(secondCompartment)
        }
    }

    data class Item(val value: Char) {
        fun priority(): Int = if (value.isUpperCase()) value.code - 38 else value.code - 96
    }
}


