package com.example.tictactoe.ui.theme

data class GameModel (
    var currentPlayer: Status = Status.PlayerX,
    var winner: Status = Status.Empty,
) {
    val isGameEnded: Boolean
        get() { return winner != Status.Empty}
}