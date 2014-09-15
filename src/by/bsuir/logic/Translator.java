package by.bsuir.logic;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Translator {
	private List<Character> alphabet;
	private boolean checkKeyWord(String keyWord){
		boolean isGoodWord = true;
		for (char character : keyWord.toCharArray()) {
			if(!alphabet.contains(character)){
				isGoodWord = false;
				break;
			}
		}
		return isGoodWord;
	}
	public Translator(){
		alphabet = new ArrayList<Character>();
		String russianAlphabet = new String("אבגדהו¸זחטיךכלםמןנסעףפץצקרשתûü‎‏ÿ");
		for (char character : russianAlphabet.toCharArray()) {
			alphabet.add(character);
		}
	}
	public String encryptText(String text, String keyWord){
		String txt = new String(text);
		txt = txt.toLowerCase();
		keyWord.toLowerCase();
		if(!checkKeyWord(keyWord)){
			JOptionPane.showMessageDialog(null, "Check key word: all character symbols of key word should be in alphabet.",
					"Check key word", 1);
			return null;
		}
		int sizeOfKeyWord = keyWord.length();
		int i = 0;
		StringBuilder sb = new StringBuilder();
		for (Character character : txt.toCharArray()) {
			if(alphabet.contains(character)){
				if(i == sizeOfKeyWord){
					i = 0;
				}
				int numberOfCodingSymbol = alphabet.indexOf(character);
				Character keyChar = keyWord.charAt(i);
				int numberOfKeySymbol = alphabet.indexOf(keyChar);
				int numberOfCipherSymbol = (numberOfCodingSymbol + numberOfKeySymbol) % 33;
				sb.append(alphabet.get(numberOfCipherSymbol));
				i++;
			}else{
				sb.append(character);
			}
		}
		return sb.toString();
	}
	public List<Character> getRussianAlphabet() {
		return alphabet;
	}
	public String decryptText(String text, String keyWord){
		StringBuilder sb = new StringBuilder();
		keyWord.toLowerCase();
		if(!checkKeyWord(keyWord)){
			JOptionPane.showMessageDialog(null, "Check key word: all character symbols of key word should be in alphabet.",
					"Check key word", 1);
			return null;
		}
		int sizeOfKeyWord = keyWord.length();
		int i = 0;
		for (Character character : text.toCharArray()) {
			if(alphabet.contains(character)){
				if(i == sizeOfKeyWord){
					i = 0;
				}
				int numberOfCipherSymbol = alphabet.indexOf(character);
				Character keyChar = keyWord.charAt(i);
				int numberOfKeySymbol = alphabet.indexOf(keyChar);
				int numberOfUncipherSymbol = (numberOfCipherSymbol - numberOfKeySymbol + 33) % 33;
				sb.append(alphabet.get(numberOfUncipherSymbol));
				i++;
			}else{
				sb.append(character);
			}
		}
		return sb.toString();
	}
}
