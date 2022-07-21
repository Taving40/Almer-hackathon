package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.example.tictactoe.ui.theme.*

class MainActivity : ComponentActivity() {
    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadGame()
        setContent {
            TicTacToeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainContent()
                }
            }
        }
    }


    @Composable
    fun MainContent() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.app_name))
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                viewModel.loadGame()
                            }
                        ) {
                            Icon(
                                Icons.Filled.Refresh,
                                contentDescription = "Reload Game"
                            )
                        }
                    }
                )
            }
        ) {
            GridButtons()
        }

    }

    @Composable
    fun GridButtons() {
        val cards: List<List<BoxModel>> by viewModel.getBoxes().observeAsState(listOf()) //list of tictactoe squares
        val currentGame: LiveData<GameModel> = viewModel.getGameStatus() //game object

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            verticalArrangement = Arrangement.Center,
        ) {
            cards.forEach { rows ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White),
                    horizontalArrangement = Arrangement.Center
                ) {
                    rows.forEach { card ->
                        ActionButton(card)
                    }
                }
            }

        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun ActionButton(card: BoxModel) {
        Card(
            modifier = Modifier
                .padding(all = 10.dp)
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(5.dp),
                )
                .height(80.dp)
                .width(80.dp),
            backgroundColor = Color.White,
            onClick = {
                viewModel.selectBoxPlayer(card)

                viewModel.selectBoxAI()
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = card.showText(),
                    fontSize = 34.sp,
                    color = Color.Black
                )
            }

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        TicTacToeTheme {
            MainContent()
        }
    }

}

