
public class HangmanLogic {
	private int _strikes=0;
	private String _word; 
	
public HangmanLogic(String word) {
	_word=word.toLowerCase();
	_strikes=0;
	
}

public int checkLetter(char letter, int from) {
	if(from <= _word.length())
	System.out.println("here2");
	return _word.indexOf(letter,from);//returns the index of the given letter or -1 if not exists
}

public String getWord() {
	return _word;
}

public void addStrike() {
	_strikes++;
}
public int getStrikes() {
	return _strikes;
}
}
