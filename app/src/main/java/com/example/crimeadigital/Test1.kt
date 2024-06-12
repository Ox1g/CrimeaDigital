package com.example.crimeadigital

import kotlin.random.Random

class FootballMatch (var team1Goals: Int = 0,var team2Goals : Int = 0) {
    fun setScore (team1Goals : Int , team2Goals : Int) {
        this.team1Goals = team1Goals
        this.team2Goals = team2Goals
    }
    override fun toString(): String {
        return "Team 1: $team1Goals - Team 2: $team2Goals"
    }
}

fun generateGoals(): Int {
    return Random.nextInt(0,6)
}

fun processing() {
    val matches = Array(10) {FootballMatch()}
    for(match in matches)
    {
        match.setScore(generateGoals(), generateGoals())
    }
    println("Исходные данные:")
    matches.forEach { println(it) }

    val nonDrawMatches = matches.filter { it.team1Goals != it.team2Goals }
    println("\nМатчи после удаления ничейных результатов:")
    nonDrawMatches.forEach { println(it) }

    val maxGoalDifference = nonDrawMatches.maxOf { Math.abs(it.team1Goals - it.team2Goals) }
    val maxDifferenceMatches = nonDrawMatches.filter { Math.abs(it.team1Goals - it.team2Goals) == maxGoalDifference }.toSet()
    println("\nМатчи с максимальным разрывом в голах:")
    maxDifferenceMatches.forEach { println(it) }
}