package com.example.tictactoe;

public class TrainingSession {
    private QLearningAI ai;

    public TrainingSession(QLearningAI ai) {
        this.ai = ai;
    }

    public void trainAI(int iterations) {
        for (int i = 0; i < iterations; i++) {
            GameState gameState = new GameState();
            char currentPlayer = 'X'; // Start with X
            boolean gameEnded = false;

            while (!gameEnded) {
                int action = ai.chooseAction(gameState);
                int row = action / 3;
                int col = action % 3;

                if (gameState.isMoveValid(row, col)) {
                    gameState.makeMove(row, col, currentPlayer);
                    String nextState = gameState.getStateAsString();

                    double reward = 0;
                    if (gameState.checkWinner(currentPlayer)) {
                        reward = 1; // Win
                        gameEnded = true;
                    } else if (gameState.isBoardFull()) {
                        reward = 0; // Draw
                        gameEnded = true;
                    } else {
                        reward = -0.1; // Small penalty for continuing
                    }

                    ai.updateQTable(gameState.getStateAsString(), action, reward, nextState);
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch player
                }
            }

            System.out.println("Iteration " + (i + 1) + " Q-Table:");
            ai.printQTable();
        }
    }
}
