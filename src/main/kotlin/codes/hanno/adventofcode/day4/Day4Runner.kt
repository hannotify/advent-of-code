package codes.hanno.adventofcode.day4

import java.nio.file.Files
import java.nio.file.Path

fun main(args: Array<String>) {
    Day4Runner().run(Path.of("src/main/resources/day4/input.txt"))
}

class Day4Runner {
    fun run(input: Path) {
        val numberOfRanges = Files.readAllLines(input).map {
            Pair(SectionAssignment(getSA(0, it)), SectionAssignment(getSA(1, it)))
        }.filter {
            it.first.overlap(it.second)
        }.count()

        println("Number of ranges: $numberOfRanges")
    }

    private fun getSA(index: Int, input: String): List<Int> {
        val truncatedInput = if (index != 0) {
            input.substring(input.indexOf(',') + 1)
        } else {
            input.substring(0, input.indexOf(','))
        }

        val split = truncatedInput.split('-')
        val start = split[0].toInt()
        val end = split[1].toInt()

        return (start .. end).toList()
    }

    data class SectionAssignment(val sections: List<Int> ) {
        fun fullyContains(otherSectionAssignment: SectionAssignment): Boolean = sections.containsAll(otherSectionAssignment.sections)
        fun overlap(otherSectionAssignment: SectionAssignment): Boolean = sections.intersect(otherSectionAssignment.sections).isNotEmpty()
    }
}