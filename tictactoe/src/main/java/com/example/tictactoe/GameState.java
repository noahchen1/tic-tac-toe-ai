package com.example.tictactoe;

public class GameState {
    private char[][] board = new char[3][3];

    public String getStateAsString() {
        StringBuilder state = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state.append(board[i][j] == '\0' ? '-' : board[i][j]);
            }
        }
        return state.toString();
    }

    public boolean isMoveValid(int row, int col) {
        return board[row][col] == '\0';
    }

    public void makeMove(int row, int col, char player) {
        board[row][col] = player;
    }

    public boolean isGameEnded() {
        return checkWinner('X') || checkWinner('O') || isBoardFull();
    }

    public boolean checkWinner(char player) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\0')
                    return false;
            }
        }
        return true;
    }
}
