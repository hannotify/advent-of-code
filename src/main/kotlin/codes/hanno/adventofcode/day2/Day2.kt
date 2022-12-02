import Day2.Choice.ChoiceCategory.*
import Day2.Outcome.WDL.*
import java.nio.file.Files
import java.nio.file.Path

fun main(args: Array<String>) {
    Day2().run(Path.of("src/main/resources/day2/input.txt"))
}

class Day2 {

    fun run(input: Path) {
        val scores = Files.lines(input).map {
            val split = it.split(" ")
            return@map Turn(Choice.valueOf(split[0]), Outcome.valueOf(split[1])).ourScore()
        }.toList();

        println(scores.sum())
    }

    data class Turn(val theirChoice: Choice, val outcome: Outcome) {
        fun ourScore(): Int = outcome.score() + theirChoice.determineOurChoice(outcome).choiceCategory.score()
    }

    enum class Choice(val choiceCategory: ChoiceCategory) {
        A(ROCK),
        B(PAPER),
        C(SCISSORS);

        fun determineOurChoice(outcome: Outcome): Choice = when (outcome.wdl) {
                WIN -> when (this.choiceCategory) {
                    ROCK -> B
                    PAPER -> C
                    SCISSORS -> A
                }
                DRAW -> this
                LOSE -> when (this.choiceCategory) {
                    ROCK -> C
                    PAPER -> A
                    SCISSORS -> B
                }
            }

        enum class ChoiceCategory {
            ROCK, PAPER, SCISSORS;

            fun score(): Int = when (this) {
                ROCK -> 1
                PAPER -> 2
                SCISSORS -> 3
            }
        }
    }

    enum class Outcome(val wdl: WDL) {
        X(LOSE),
        Y(DRAW),
        Z(WIN);

        fun score(): Int = when(this.wdl) {
            LOSE -> 0
            DRAW -> 3
            WIN -> 6
        }

        enum class WDL {
            WIN, DRAW, LOSE
        }
    }
}


