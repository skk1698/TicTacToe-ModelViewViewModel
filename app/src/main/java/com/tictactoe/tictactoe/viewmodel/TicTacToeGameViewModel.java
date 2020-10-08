package com.tictactoe.tictactoe.viewmodel;

import androidx.databinding.ObservableArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tictactoe.tictactoe.model.Cell;
import com.tictactoe.tictactoe.model.Player;
import com.tictactoe.tictactoe.model.TicTacToeGameEngine;
import com.tictactoe.tictactoe.utilities.StringUtility;

public class TicTacToeGameViewModel extends ViewModel {

    public ObservableArrayMap<String, String> cells;
    public TicTacToeGameEngine ticTacToeGameEngine;

    public void init() {
        ticTacToeGameEngine = new TicTacToeGameEngine();
        cells = new ObservableArrayMap<>();
    }

    public void onClickedCellAt(int row, int column) {
        if (ticTacToeGameEngine.cells[row][column] == null) {
            ticTacToeGameEngine.cells[row][column] = new Cell(ticTacToeGameEngine.currentPlayer);
            cells.put(StringUtility.stringFromNumbers(row, column), ticTacToeGameEngine.currentPlayer.symbol);
            if (ticTacToeGameEngine.hasGameEnded()) {
                ticTacToeGameEngine.reset();
            } else {
                ticTacToeGameEngine.switchPlayersTurn();
            }
        }
    }

    public LiveData<Player> getGameWinner() {
        return ticTacToeGameEngine.winner;
    }
}
