package com.example.tictactoe.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    private val gameStatus: MutableLiveData<GameModel> by lazy {
        MutableLiveData<GameModel>()
    }

    private val boxes: MutableLiveData<MutableList<MutableList<BoxModel>>> by lazy {
        MutableLiveData<MutableList<MutableList<BoxModel>>>()
    }

    fun getGameStatus(): LiveData<GameModel> {
        return gameStatus
    }

    fun getBoxes(): LiveData<MutableList<MutableList<BoxModel>>> {
        return boxes
    }

    fun loadGame() {
        var indexColumn: Int = 0
        var indexRow: Int = 0

        boxes.value = MutableList(3) {
            indexRow = 0

            MutableList(3) {
                BoxModel(
                    indexColumn = indexColumn++ / 3,
                    indexRow = indexRow++
                )
            }
        }

        gameStatus.value = GameModel()
    }

    /**
     * selectBox iterates over the boxes matrix and checks if the current
     * element is the box we are selecting. then it changes it and finally it
     * overwrites the boxes matrix with the newly made matrix
     * TODO: remove the needless new matrix and change the boxes matrix directly
     */
    fun selectBoxPlayer(box: BoxModel) {
        var currantPlayer: Status = gameStatus.value?.currentPlayer!!
        var hasModification: Boolean = false

        var list: MutableList<MutableList<BoxModel>> = boxes.value?.map { columns ->
            var newColumns = columns.map { row ->
                if (box.indexColumn == row.indexColumn && box.indexRow == row.indexRow) {
                    if (row.status == Status.Empty) {
                        hasModification = true
                        row.status = currantPlayer
                    }
                }

                row
            }

            newColumns
        } as MutableList<MutableList<BoxModel>>

        if (hasModification && gameStatus.value?.isGameEnded == false) {
            gameStatus.value?.currentPlayer = gameStatus.value?.currentPlayer!!.next()
            boxes.value?.removeAll { true }
            boxes.value = list
        }

        checkGameEnded()
    }

    fun selectBoxAI(){
        //TODO
    }

    /**
     * checks if the game has ended and writes the winner in gameStatus
     * TODO: check the boxes matrix more nicely
     */
    private fun checkGameEnded() { //24 25 26
        gameStatus.value!!.winner = Status.Empty
    }
}