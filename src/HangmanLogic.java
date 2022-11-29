public class HangmanLogic {
	private int _strikes=0;
	private String _word; 

	public HangmanLogic(String word) {
		_word=word.toLowerCase();
		_strikes=0;
		System.out.println(word);
	}

	public int checkLetter(char letter, int from) {
		return _word.indexOf(letter,from);//returns the index of the given letter or -1 if not exists
	}
	/*
	 * This method will return _word
	 */
	public String getWord() {
		return _word;
	}
	/*
	 * This method will add 1 to _strikes variable
	 */
	public void addStrike() {
		_strikes++;
	}
	/*
	 * This method will return _strikes
	 */
	public int getStrikes() {
		return _strikes;
	}
	/*
	 * This method will reset the object values to begin a new game
	 */
	public void reset (String word){
		_word=word;//sets a different word for the new game
		_strikes=0;//resets strikes 
	}
}
