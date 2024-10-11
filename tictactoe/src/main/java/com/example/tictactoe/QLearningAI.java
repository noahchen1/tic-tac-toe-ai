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

    public void updateQTable(String state, int action, double reward, String nextState) {
        double currentQ = qTable.getOrDefault(state, new HashMap<>()).getOrDefault(action, 0.0);
        double maxNextQ = qTable.getOrDefault(nextState, new HashMap<>())
                .values().stream().max(Double::compare).orElse(0.0);

        double updatedQ = currentQ + learningRate * (reward + discountFactor * maxNextQ - currentQ);
        qTable.computeIfAbsent(state, k -> new HashMap<>()).put(action, updatedQ);
    }

    private int chooseRandomAction(GameState gameState) {
        Random rand = new Random();
        int row, col;
        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while (!gameState.isMoveValid(row, col));
        return row * 3 + col;
    }

    public void printQTable() {
        System.out.println("Current Q-Table:");
        for (Map.Entry<String, Map<Integer, Double>> entry : qTable.entrySet()) {
            System.out.print("State: " + entry.getKey() + " -> ");
            System.out.println(entry.getValue());
        }
    }

}
