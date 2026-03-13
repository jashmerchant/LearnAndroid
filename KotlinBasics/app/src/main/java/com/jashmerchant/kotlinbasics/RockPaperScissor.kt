package com.jashmerchant.kotlinbasics

fun main() {
    var playerPlays: String
    var computerPlays: String

    println("Please enter your choice" +
            "\n" + "1) Rock" +
            "\n" + "2) Paper" +
            "\n" + "3) Scissor")

    val userInput = readln().toInt()
    playerPlays = getChoice(userInput)
    if (playerPlays == "Invalid Choice") {
        println("Please choose a valid input")
        return
    }

    val compInput = (1..3).random() // 1 to 3 inclusive
    computerPlays = getChoice(compInput)

    println("Player played: $playerPlays")
    println("Computer played: $computerPlays")

    println(getResult(playerPlays, computerPlays) + "!")
}

fun getChoice(num: Int): String {
    return when (num) {
        1 -> {
            "Rock"
        }
        2 -> {
            "Paper"
        }
        3 -> {
            "Scissor"
        }
        else -> {
            "Invalid Choice"
        }
    }
}

fun getResult(choice1: String, choice2: String): String {
    if ((choice1 == "Rock" && choice2 == "Paper") ||(choice1 == "Paper" && choice2 == "Rock")) return "Paper"
    else if ((choice1 == "Scissor" && choice2 == "Paper") || (choice1 == "Paper" && choice2 == "Scissor")) return "Scissors"
    else if ((choice1 == "Rock" && choice2 == "Scissor") || (choice1 == "Scissor" && choice2 == "Rock")) return "Rock"
    else return "Draw"
}