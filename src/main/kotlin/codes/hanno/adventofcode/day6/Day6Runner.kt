package codes.hanno.adventofcode.day6

import java.nio.file.Files
import java.nio.file.Path

fun main(args: Array<String>) {
    Day6Runner().run(Path.of("src/main/resources/day6/input.txt"))
}

fun detectMarker(input: String, numberOfCharacters: Int): Int {
    var index = 0
    var substring = input.substring(index, index + numberOfCharacters)

    while (substring.toCharArray().toSet().size < numberOfCharacters) {
        substring = input.substring(++index, index + numberOfCharacters)
    }

    return index + numberOfCharacters
}

class Day6Runner {
    fun run(input: Path) {
        val markerIndex = Files.readAllLines(input).map {
            detectMarker(it, 14)
        }.joinToString()

        print(markerIndex)
    }
}
