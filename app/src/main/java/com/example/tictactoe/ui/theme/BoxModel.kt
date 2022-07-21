package com.example.tictactoe.ui.theme

class BoxModel(
    var status: Status = Status.Empty,
    var indexColumn: Int = 0,
    var indexRow: Int = 0

) {
    fun showText(): String {
        return when(status) {
            Status.Empty -> ""
            Status.AiO -> "O"
            Status.PlayerX -> "X"
        }
    }
}