import java.io.FileNotFoundException;
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

	public final int NUMBER_OF_LETTERS=26;//number of letters in the ABC
	public final int BUTTONS_IN_A_ROW=7;//number of buttons in a row
	private Button[] btns;//the array that will contain the buttons
	private final int NUMBER_OF_STRIKES=10;//number of allowed strikes
	private Shape[] shapes;//shapes array that will create the draw of the Hangman
	private String labelText="";//the text of the label
	private char letter='a';
	int result;
	HangmanLogic game;//the game object from HangmanLogic class

	public void initialize() throws FileNotFoundException {
		ReadFile.openFile();//opens the text file with the list of words
		game= new HangmanLogic(ReadFile.getWord());//creates the game
		btns = new Button[NUMBER_OF_LETTERS];//array of buttons
		//Initializing shapes array
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
		shapes[9]=tenthLine;
		setInitialLabelText();//sets the initial text of the label to be _ at the length of the word
		for (int i = 0; i < btns.length; i++) {//creates the buttons from a-z and attaches a function to the buttons
			btns[i] = new Button(letter+ "");//adds a text to the button
			btns[i].setPrefSize(btnGrid.getPrefWidth()/BUTTONS_IN_A_ROW, btnGrid.getPrefHeight()/BUTTONS_IN_A_ROW);//sets the size to fit the window
			btnGrid.add(btns[i], i%BUTTONS_IN_A_ROW , i/BUTTONS_IN_A_ROW);//adds the button to the grid
			letter++;//goes to the next char

			btns[i].setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent event) {
					try {
						handleButton(event);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}	
			}); 


		}

	}
	private void handleButton(ActionEvent event) throws FileNotFoundException {
		result=0;
		Button btn=(Button) event.getSource();//gets the button the was pressed
		btn.setDisable(true);
		char c= btn.getText().charAt(0);
		result=game.checkLetter(c,0);
		if(result==-1) {
			strike();//adds a strike

			if (game.getStrikes()==NUMBER_OF_STRIKES) {
				gameLost();
			}
			return;//stops the running of the functions for this button press if it was not correct
		}
		setLabelText(result, c);
		int last= game.getWord().lastIndexOf(c);
		while(result!=last) {//checks if it is not the last letter
			result=game.checkLetter(c,result+1);
			setLabelText(result, c);
		}
		if (result==last) {
			setLabelText(result, c);
		}
	}

	private void setLabelText(int i, char c) throws FileNotFoundException {
		char[] textArray=labelText.toCharArray();
		textArray[i]=c;
		labelText=String.valueOf(textArray);
		textBox.setText(labelText);
		if(checkIfGameWon())
			gameWon();

	}
	/**
	 * This method sets the initial text of the label 
	 * @param length int representing the length of the word the user need to guess correctly in the hangman game
	 */
	private void setInitialLabelText() {
		int length=game.getWord().length();//gets the length of the word the player need to guess
		for (int i=0; i<length; i++) {
			labelText= labelText+ "_";

		}
		textBox.setText(labelText);
	}

	private void strike() {
		game.addStrike();
		shapes[game.getStrikes()-1].setVisible(true);//set visibility of the shape
	}

	private boolean checkIfGameWon() {
		if (labelText.indexOf('_')==-1)
			return true;
		else 
			return false;
	}


	private void gameWon() throws FileNotFoundException {
		int answer = JOptionPane.showOptionDialog(null, "Game Won! \nThe word was " + game.getWord()+"\nDo you want to play another game?","Hangman", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null , null , 0);
		if(answer!=0) {
			ReadFile.closeInput();//closes the Text file
			System.exit(0);//stops the game from running
		}
		else {
			reset();
		}
	}

	private void gameLost() throws FileNotFoundException {
		int answer = JOptionPane.showOptionDialog(null, "Gameover \nThe word was " + game.getWord()+ "\nDo you want to play another game?","Hangman", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null , null , 0);
		if(answer!=0) {
			ReadFile.closeInput();//closes the Text file
			System.exit(0);//stops the game from running
		}
		else {
			reset();
		}
	}

	private void reset() throws FileNotFoundException {
		
		for (Shape shape : shapes) {//sets all the shapes to be non visible
			shape.setVisible(false);	
		}

		for (Button button : btns) {//enables the buttons which were pressed and disabled 
			button.setDisable(false);
		}
		
		game.reset(ReadFile.getWord());
		labelText="";//sets an empty text before putting the underscores
		setInitialLabelText();

	}
}
