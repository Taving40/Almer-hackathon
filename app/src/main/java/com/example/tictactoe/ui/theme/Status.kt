package com.example.tictactoe.ui.theme

enum class Status {
    Empty,
    PlayerX,
    AiO;

    fun next(): Status {
        if (this == PlayerX){
            return AiO
        }
        return PlayerX
    }
}