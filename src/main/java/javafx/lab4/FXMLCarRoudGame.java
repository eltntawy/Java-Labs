package main.java.javafx.lab4;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLCarRoudGame extends Application {

	@Override
	public void start(Stage primaryStage) {

		FXMLLoader loader = new FXMLLoader();
		try {
			Parent root = loader.load(getClass().getResource(
					"FXMLCarRoudGame.fxml").openStream());
			FXMLCarRoudGameController carRoudGameController = (FXMLCarRoudGameController) loader
					.getController();

			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
