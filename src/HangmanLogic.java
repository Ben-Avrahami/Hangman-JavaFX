
public class HangmanLogic {
	private int strikes=0;
	private String _word; 
	
public HangmanLogic(String word) {
	_word=word;
}

public int checkLetter(char letter, int from) {
	return _word.indexOf(letter,from);//returns the index of the given letter or -1 if not exists
}
public String getWord() {
	return _word;
}
public void addStrike() {
	strikes++;
}
}
