import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Hangman extends Application{
	public void start(Stage stage) throws Exception{
		Parent root = (Parent)

				FXMLLoader.load(getClass().getResource("Hangman.fxml"));

		Scene scene = new Scene(root);
		stage.setTitle("Hangman");
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
		System.out.println();
	}
}