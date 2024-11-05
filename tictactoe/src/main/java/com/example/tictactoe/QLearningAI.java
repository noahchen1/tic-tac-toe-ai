package com.example.tictactoe;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class QLearningAI {
    private Map<String, Map<Integer, Double>> qTable = new HashMap<>();
    private double learningRate = 0.1;
    private double discountFactor = 0.95;
    private double explorationRate = 0.2;

    public int chooseAction(GameState gameState) {
        String state = gameState.getStateAsString();
        Map<Integer, Double> stateActions = qTable.getOrDefault(state, new HashMap<>());

        if (Math.random() < explorationRate) {
            return chooseRandomAction(gameState);
        } else {
            return stateActions.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(chooseRandomAction(gameState));
        }
    }

    public int chooseRandomAction(GameState gameState) {
        Random rand = new Random();
        int row, col;
    
        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while (!gameState.isMoveValid(row, col));
    
        return row * 3 + col;
    }     
}
