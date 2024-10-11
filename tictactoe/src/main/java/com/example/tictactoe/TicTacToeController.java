package com.example.tictactoe;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tictactoe")
public class TicTacToeController {
    private QLearningAI ai = new QLearningAI();

    @PostMapping("/start")
    public String startGame() {
        return "Game started! Make your move.";
    }

    @PostMapping("/move")
    public String makeMove(@RequestParam int row, @RequestParam int col) {
        GameState gameState = new GameState();
        // Assume player 'X' is human and 'O' is AI
        if (gameState.isMoveValid(row, col)) {
            gameState.makeMove(row, col, 'X'); // Player move
            String stateAfterPlayerMove = gameState.getStateAsString();

            // Check if player wins or game ends
            if (gameState.checkWinner('X')) {
                return "Player X wins!";
            } else if (gameState.isBoardFull()) {
                return "It's a draw!";
            }

            // AI move
            int aiAction = ai.chooseAction(gameState);
            int aiRow = aiAction / 3;
            int aiCol = aiAction % 3;

            gameState.makeMove(aiRow, aiCol, 'O'); // AI move
            String nextState = gameState.getStateAsString();

            double reward = gameState.checkWinner('O') ? 1 : (gameState.isBoardFull() ? 0 : -0.1);
            ai.updateQTable(stateAfterPlayerMove, aiAction, reward, nextState);

            if (gameState.checkWinner('O')) {
                return "Player O (AI) wins!";
            } else if (gameState.isBoardFull()) {
                return "It's a draw!";
            }

            return "Your move made. AI played its turn.";
        } else {
            return "Invalid move. Try again.";
        }
    }
}
