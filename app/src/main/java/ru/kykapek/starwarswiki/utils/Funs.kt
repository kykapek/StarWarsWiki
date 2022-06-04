package ru.kykapek.starwarswiki.utils

fun episodeIdToNumber(episodeId: Int): Int {
    var number = 0
    when (episodeId) {
        4 -> number = 1
        5 -> number = 2
        6 -> number = 3
        1 -> number = 4
        2 -> number = 5
        3 -> number = 6
    }
    return number
}