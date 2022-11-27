import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class HangmanController {

	@FXML
	private GridPane btnGrid;

	@FXML
	private Line eightLine;

	@FXML
	private Circle fifthLine;

	@FXML
	private Line firstLine;

	@FXML
	private Line forthLine;

	@FXML
	private Line ninthLine;

	@FXML
	private Line secondLine;

	@FXML
	private Line seventhLine;

	@FXML
	private Line sixthLine;

	@FXML
	private Line tenthLine;

	@FXML
	private Label textBox;

	@FXML
	private Line thirdLine;

	public final int NUMBER_OF_LETTERS=26;
	public final int BUTTONS_IN_A_ROW=7;
	private Button[] btns;

	private char letter='a';

	public void initialize() {
		btns = new Button[NUMBER_OF_LETTERS];		
		for (int i = 0; i < btns.length; i++) {
			btns[i] = new Button(letter+ "");
			btns[i].setPrefSize(btnGrid.getPrefWidth()/BUTTONS_IN_A_ROW, btnGrid.getPrefHeight()/BUTTONS_IN_A_ROW);
			btnGrid.add(btns[i], i%BUTTONS_IN_A_ROW , i/BUTTONS_IN_A_ROW);
			letter++;
			
			btns[i].setOnAction(new EventHandler<ActionEvent>(){
				
				public void handle(ActionEvent event) {
					handleBtn(event);
				}
			
				
			});
		
		}
	}
		
		private void handleBtn(ActionEvent event) {
			System.out.println("test");
		}



	}


