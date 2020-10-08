package com.tictactoe.tictactoe.model;

import androidx.lifecycle.MutableLiveData;

import static com.tictactoe.tictactoe.utilities.StringUtility.isNullOrEmpty;

public class TicTacToeGameEngine {

    private static final int BOARD_SIZE = 3;
    private static final String FIRST_PLAYER = "Player 1";
    private static final String SECOND_PLAYER = "Player 2";
    private static final String FIRST_PLAYER_SYMBOL = "X";
    private static final String SECOND_PLAYER_SYMBOL = "O";

    public Player player1;
    public Player player2;

    public Player currentPlayer;
    public Cell[][] cells;

    public MutableLiveData<Player> winner = new MutableLiveData<>();

    public TicTacToeGameEngine() {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        player1 = new Player(FIRST_PLAYER, FIRST_PLAYER_SYMBOL);
        player2 = new Player(SECOND_PLAYER, SECOND_PLAYER_SYMBOL);
        currentPlayer = player1;
    }

    public boolean hasGameEnded() {
        if (hasThreeHorizontalCellsAreSame() || hasThreeVerticalCellsAreSame() || hasThreeDiagonalCellsAreSame()) {
            winner.setValue(currentPlayer);
            return true;
        }

        if (isTicTacToeBoardFull()) {
            winner.setValue(null);
            return true;
        }

        return false;
    }

    public boolean hasThreeHorizontalCellsAreSame() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (areEqualCells(cells[i][0], cells[i][1], cells[i][2])) {
                return true;
            }
        }
        return false;
    }

    public boolean hasThreeVerticalCellsAreSame() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (areEqualCells(cells[0][i], cells[1][i], cells[2][i])) {
                return true;
            }
        }
        return false;
    }

    public boolean hasThreeDiagonalCellsAreSame() {
        return areEqualCells(cells[0][0], cells[1][1], cells[2][2]) ||
                areEqualCells(cells[0][2], cells[1][1], cells[2][0]);

    }

    public boolean isTicTacToeBoardFull() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell == null || cell.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean areEqualCells(Cell... cells) {
        if (cells == null || cells.length == 0) {
            return false;
        }

        for (Cell cell : cells) {
            if (cell == null || isNullOrEmpty(cell.player.symbol)) {
                return false;
            }
        }

        Cell comparisonBase = cells[0];
        for (int i = 1; i < cells.length; i++) {
            if (!comparisonBase.player.symbol.equals(cells[i].player.symbol)) {
                return false;
            }
        }
        return true;
    }

    public void switchPlayersTurn() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    public void reset() {
        player1 = null;
        player2 = null;
        currentPlayer = null;
        cells = null;
    }
}
