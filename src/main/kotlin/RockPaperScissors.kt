/**
Opponent hand
A - Rock
B - Paper
C - Scissor

Our hand
X - Rock
Y - Paper
Z - Scissor

Points

Rock defeats Scissors, Scissors defeats Paper, and Paper defeats Rock.

1 If we choose rock
2 If we choose paper
3 if we choose scissor

0 points of we lose
3 points if we draw
6 points if we win
 **/


fun main() {
    val rawData = FileReader("rockPaperScissor.txt").getRawData()
    if (rawData.isEmpty()) {
        print("No data found")
        return
    }

    val hashGameMoves = hashMapOf<Char, String>()
    hashGameMoves['A'] = "ROCK"
    hashGameMoves['B'] = "PAPER"
    hashGameMoves['C'] = "SCISSOR"
    hashGameMoves['X'] = "ROCK"
    hashGameMoves['Y'] = "PAPER"
    hashGameMoves['Z'] = "SCISSOR"

    val hashPart2Rules = hashMapOf<Char, String>()
    hashPart2Rules['X'] = "LOSE"
    hashPart2Rules['Y'] = "DRAW"
    hashPart2Rules['Z'] = "WIN"

    val hashGamePoints = hashMapOf<String, Int>()
    hashGamePoints["ROCK"] = 1
    hashGamePoints["PAPER"] = 2
    hashGamePoints["SCISSOR"] = 3

    println(part1(rawData, hashGameMoves, hashGamePoints))
    println(part2(rawData, hashGameMoves, hashPart2Rules, hashGamePoints))
}

fun part1(rawData: List<String>, hashGameMoves: HashMap<Char, String>, hashGamePoints: HashMap<String, Int>): Int {

    var ourTotalScore = 0
    repeat(rawData.size) {
        val opponentMove = hashGameMoves.getOrDefault(rawData[it][0], "")
        val ourMove = hashGameMoves.getOrDefault(rawData[it][2], "")

        val score = calculateGameScore(opponentMove, ourMove, hashGamePoints)
        ourTotalScore += score
    }
    return ourTotalScore
}

private fun calculateGameScore(opponentMove: String, ourMove: String, hashGamePoints: HashMap<String, Int>): Int {

    val losingPoints = 0
    val drawPoints = 3
    val winningPoints = 6

    return if (opponentMove == ourMove) {
        //Draw
        hashGamePoints.getOrDefault(ourMove, 0) + drawPoints
    } else if (ourMove == "ROCK" && opponentMove == "SCISSOR"
        || ourMove == "SCISSOR" && opponentMove == "PAPER"
        || ourMove == "PAPER" && opponentMove == "ROCK"
    ) {
        // We win
        hashGamePoints.getOrDefault(ourMove, 0) + winningPoints
    } else {
        hashGamePoints.getOrDefault(ourMove, 0) + losingPoints
    }
}

fun part2(
    rawData: List<String>,
    hashGameMoves: HashMap<Char, String>,
    hashPart2Rules: HashMap<Char, String>,
    hashGamePoints: HashMap<String, Int>
): Int {
    var ourTotalScore = 0
    repeat(rawData.size) {
        val opponentMove = hashGameMoves.getOrDefault(rawData[it][0], "")
        val roundPoints: Int
        val ourPlay = when (hashPart2Rules.getOrDefault(rawData[it][2], "")) {
            "LOSE" -> {
                // loose
                roundPoints = 0
                when (opponentMove) {
                    "ROCK" -> "SCISSOR"
                    "PAPER" -> "ROCK"
                    "SCISSOR" -> "PAPER"
                    else -> "PAPER"
                }
            }

            "DRAW" -> {
                //draw
                roundPoints = 3
                opponentMove
            }

            else -> {
                // win
                roundPoints = 6
                when (opponentMove) {
                    "SCISSOR" -> "ROCK"
                    "PAPER" -> "SCISSOR"
                    "ROCK" -> "PAPER"
                    else -> "PAPER"
                }
            }
        }
        ourTotalScore += hashGamePoints.getOrDefault(ourPlay, 0) + roundPoints
    }
    return ourTotalScore
}