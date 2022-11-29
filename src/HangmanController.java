import javax.swing.JOptionPane;
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
	private final int NUMBER_OF_STRIKES=10;
	private Shape[] shapes;//shapes array
	private int strikes=0;
	private String labelText="";
	private char letter='a';
	int result;
	HangmanLogic game;

	public void initialize() {
		game= new HangmanLogic("armadilo");//creates the game
		btns = new Button[NUMBER_OF_LETTERS];//array of buttons
		shapes=new Shape[NUMBER_OF_STRIKES];
		shapes[0]= firstLine;
		shapes[1]=secondLine;
		shapes[2]=thirdLine;
		shapes[3]=forthLine;
		shapes[4]=fifthLine;
		shapes[5]=sixthLine;
		shapes[6]=seventhLine;
		shapes[7]=eightLine;
		shapes[8]=ninthLine;
		shapes[9]=tenthLine;//shapes array
		setInitialLabelText(game.getWord().length());//sets the inital text of the label to be _ at the length of the word
		for (int i = 0; i < btns.length; i++) {//creates the buttons from a-z and attaches a function to the buttons
			btns[i] = new Button(letter+ "");//adds a text to the button
			btns[i].setPrefSize(btnGrid.getPrefWidth()/BUTTONS_IN_A_ROW, btnGrid.getPrefHeight()/BUTTONS_IN_A_ROW);//sets the size to fit the window
			btnGrid.add(btns[i], i%BUTTONS_IN_A_ROW , i/BUTTONS_IN_A_ROW);//adds the button to the grid
			letter++;//goes to the next char

			btns[i].setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent event) {
					handleButton(event);
				}	
			});


		}

	}
	private void handleButton(ActionEvent event) {
		result=0;
		Button btn=(Button) event.getSource();//gets the button the was pressed
		btn.setDisable(true);
		if(strikes<NUMBER_OF_STRIKES) {//if the game is still continuing
			char c= btn.getText().charAt(0);
			System.out.println("here");
			result=game.checkLetter(c,0);
			System.out.println("here");
			if(result==-1) {
				strike();//adds a strike	
				if (game.getStrikes()==NUMBER_OF_STRIKES) {
					int answer = JOptionPane.showOptionDialog(null, "Gameover, do you want to play another game?","Hangman", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null , null , 0);
					if(answer!=0)
						return;//stops the game from running
					//else {
					//create a replay function
					//}
				}
				return;//stops the running of the functions for this button press if it was not correct
			}
			setLabelText(result, c);
			int last= game.getWord().lastIndexOf(c);
			while(result!=last) {
				result=game.checkLetter(c,result+1);
				setLabelText(result, c);
			}

		}

		else 
			System.out.println("game is over");
	}

	private void setLabelText(int i, char c) {
		char[] textArray=labelText.toCharArray();
		textArray[i]=c;
		labelText=String.valueOf(textArray);
		textBox.setText(labelText);

	}
	/**
	 * This method sets the initial text of the label 
	 * @param length int representing the length of the word the user need to guess correctly in the hangman game
	 */
	private void setInitialLabelText(int length) {
		for (int i=0; i<length; i++) {
			labelText= labelText+ "_";

		}
		textBox.setText(labelText);
	}
	private void strike() {
		game.addStrike();
		shapes[game.getStrikes()-1].setVisible(true);//set visibility of the shape
	}



}


