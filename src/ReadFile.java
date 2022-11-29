import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class ReadFile {

	private static Scanner input;
	private static int counter=0;
	private static Random rand = new Random();
	private static int randomValue;
	private static ArrayList<Integer> wordsChosenAlready = new ArrayList<Integer>();
	private static String str;
	private static File WordList=new File("WordList.txt");
	/*
	 * This Method opens the file and counts how many words are in the file
	 */
	public static void openFile() throws FileNotFoundException {
		input = new Scanner(WordList);

		while (input.hasNext()){
			str = input.next();
			counter++;
		}
	}
	/*
	 * This method returns a random word from the text file and makes sure the word wasnt the chosen word in a previous game
	 */
	public static String getWord() throws FileNotFoundException {
		input = new Scanner(WordList);
		randomValue = generateRandomNumber();
		for (Integer wordNumber : wordsChosenAlready) {//if the word was already chosen in the previous games, will choose another word
			if (wordNumber==randomValue)
				getWord();

		}
		wordsChosenAlready.add(randomValue);//adds the number of the random word to the arraylist to make sure it is not chosen again

		for (int i = 0; i < randomValue && input.hasNext() ; i++) {
			str = input.next();

		}
		return str;//returns the string
	}
	/*
	 * This method closes the file
	 */
	public static void closeInput() {
		input.close();
	}
	
	/*
	 * This method generated a random number
	 */
	private static int generateRandomNumber() {
		return rand.nextInt(counter);

	}

}
