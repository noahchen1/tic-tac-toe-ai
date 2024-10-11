package com.example.tictactoe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class }) // Exclude DataSource auto-configuration
public class TictactoeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TictactoeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		QLearningAI ai = new QLearningAI();
		TrainingSession trainingSession = new TrainingSession(ai);
		trainingSession.trainAI(10); // Train the AI with a desired number of iterations
		System.out.println("AI Training Complete");
	}
}
