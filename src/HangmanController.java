import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

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
	private Shape[] shapes;
	private final int NUMBER_OF_STRIKES=10;
	private int strikes=0;
	private String labelText="_ _ _ _ _ _ _ _ _ _ _";
	private char letter='a';
	HangmanLogic game;

	public void initialize() {
		HangmanLogic game= new HangmanLogic("letters");
		btns = new Button[NUMBER_OF_LETTERS];
		Shape shapes[]={firstLine,secondLine,thirdLine,forthLine,fifthLine,sixthLine,seventhLine,eightLine,ninthLine,tenthLine};
		textBox.setText(labelText);//sets the inital text of the label
		shapes[3].setVisible(true);//set visibility of the shape 
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
		Button btn=(Button) event.getSource();
		if(strikes<NUMBER_OF_STRIKES) {
			System.out.println(btn.getText());
			char c= btn.getText().charAt(0);
			int result=game.checkLetter(c,0);
			System.out.println(btn.getText());

			int last= game.getWord().lastIndexOf(c);
			if (result!= last)
			{
				setLabelText(result, c);
				result=game.checkLetter(c,result+1);
			}
			btn.setDisable(true);
			
			
		}
		else 
			System.out.println("game is over");
	}
	
	private void setLabelText(int i, char c) {
		i=i*2;//becuase of the whitespaces
		char[] textArray=labelText.toCharArray();
		textArray[i]=c;
		labelText=String.valueOf(textArray);
		textBox.setText(labelText);
		
	}



}


