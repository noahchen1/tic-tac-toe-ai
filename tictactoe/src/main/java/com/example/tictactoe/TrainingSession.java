package com.example.tictactoe;

public class TrainingSession {
    private QLearningAI ai;

    public TrainingSession(QLearningAI ai) {
        this.ai = ai;
    }

    public void trainAI(int iterations) {
        for (int i = 0; i < iterations; i++) {
            GameState gameState = new GameState();
            char currPlayer = 'X';
            boolean gameEnded = false;

            while (!gameEnded) {
                int action = ai.chooseAction(gameState);
                int row = action / 3;
                int col = action % 3;

                if (gameState.isMoveValid(row, col))
            }
        }
    }
}